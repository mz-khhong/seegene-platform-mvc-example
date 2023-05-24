package com.seegene.mvnpoc.security;

import com.seegene.mvnpoc.support.convert.DefaultDateTimeConverter;
import com.seegene.mvnpoc.support.convert.DefaultDateTimeFormat;
import com.seegene.mvnpoc.support.exception.EntityNotFoundException;
import com.seegene.mvnpoc.web.api.v1.demo.domain.dto.DecodeJwt;
import com.seegene.mvnpoc.web.api.v1.demo.domain.dto.ToKenResponseRecord;
import com.seegene.mvnpoc.web.api.v1.demo.domain.dto.UserInfoRecord;
import com.seegene.mvnpoc.web.api.v1.demo.domain.entity.UserEntity;
import com.seegene.mvnpoc.web.api.v1.demo.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

/**
 * class 한글 설명
 * <PRE>
 * </PRE>
 *
 * @author : @mz.co.kr
 * @History <PRE>
 * No  Date           Author            Desc
 * ---- ------------ ---------------- ------------------------------------
 * 1                 최초작성
 * </PRE>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationProvider {

    private static final String ROLES = "roles";

    @Value("${jwt.token-validity-in-seconds}")
    private long accessTokenValidity;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenValidity;

    @Value("${jwt.server.api-auth-key}")
    private String apiAuthKey;

    private final UserRepository userRepository;

    private final PlatformUserDetailsService platformUserDetailsService;

    public String getApiAuthKey() {
        return apiAuthKey;
    }

    // for webToken
    public ToKenResponseRecord createWebToken(UserInfoRecord userInfoRecord) {

        long now = (new Date()).getTime();
        Date current_validity = new Date();
        Date access_token_validity = new Date(now + this.accessTokenValidity * 1000);
        Date refresh_token_validity = new Date(now + this.refreshTokenValidity * 1000);

        String access_token = "";
        String refresh_token = "";

        // byte 변환
        byte[] secretKeyBytes = Base64.getEncoder().encode(apiAuthKey.getBytes());
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

        try {
            access_token = Jwts.builder()
                    .setSubject(userInfoRecord.userName())
                    .setIssuedAt(new Date(now))
                    .setExpiration(access_token_validity)
                    .claim(ROLES, userInfoRecord.userRole())
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
            log.info(String.format("[createAccessToken] %s", access_token));

            refresh_token = Jwts.builder()
                    .setSubject(userInfoRecord.userName())
                    .setIssuedAt(new Date(now))
                    .claim(ROLES, userInfoRecord.userRole())
                    .setExpiration(refresh_token_validity)
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
            log.info(String.format("[createRefreshToken] %s", refresh_token));

        } catch (Exception e) {
            e.printStackTrace();
        }
        String createDatetime = DefaultDateTimeConverter.convertDateTime(current_validity.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), DefaultDateTimeFormat.DATE_TIME_FORMAT);
        String accessToken_expireDateTime = DefaultDateTimeConverter.convertDateTime(access_token_validity.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), DefaultDateTimeFormat.DATE_TIME_FORMAT);
        String refreshToken_expireDateTime = DefaultDateTimeConverter.convertDateTime(refresh_token_validity.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), DefaultDateTimeFormat.DATE_TIME_FORMAT);

        return new ToKenResponseRecord(access_token, refresh_token, createDatetime, accessToken_expireDateTime, createDatetime, refreshToken_expireDateTime);

    }

    public ToKenResponseRecord changeToken(String token) throws JwtException {
        DecodeJwt decodeJwt = DecodeJwt.decode(token.substring("Bearer ".length()));
        UserEntity userEntity = userRepository.findByUserName(decodeJwt.preferred_username()).orElseThrow(() -> new EntityNotFoundException());
        UserInfoRecord userInfoRecord = new UserInfoRecord(userEntity.getId(), userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getUserWorkStatus(), userEntity.getUserRole());
        return createWebToken(userInfoRecord);
    }

//    public boolean validateToken(String token) throws JwtException {
//        if(StringUtils.hasText(token)) {
//            try {
//                Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(Base64.getEncoder().encode(apiAuthKey.getBytes())).build().parseClaimsJws(webToken.accessToken());
//                log.info(claims.getBody().getExpiration() + "");
//                return !claims.getBody().getExpiration().before(new Date());
//            } catch (SecurityException | MalformedJwtException e) {
//                log.info("잘못된 jwt 서명");
//            } catch (ExpiredJwtException e) {
//                log.info("만료된 jwt 토큰");
//            } catch (UnsupportedJwtException e) {
//                log.info("지원되지 않는 jwt 토큰");
//            } catch (IllegalArgumentException e) {
//                log.info("jwt 토큰 오류");
//                log.info(e.getMessage());
//            }
//        }
//        return false;
//    }

    public Authentication getAuthentication(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(Base64.getEncoder().encode(apiAuthKey.getBytes())).build().parseClaimsJws(token);
        String role = claims.getBody().get("roles").toString();

        return new UsernamePasswordAuthenticationToken(
                token,
                null,
                Collections.singleton(new SimpleGrantedAuthority(role))
        );
    }

//    public Authentication getAuthentication(String token) {
//        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(Base64.getEncoder().encode(apiAuthKey.getBytes())).build().parseClaimsJws(token.substring("Bearer ".length()));
//        String role = claims.getBody().get("roles").toString();
//
//        return new UsernamePasswordAuthenticationToken(
//                token,
//                null,
//                Collections.singleton(new SimpleGrantedAuthority(role))
//        );
//    }

    private Key getSigninKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        if(authentication.getName()!=null && authentication.getName()!=""){
        UserDetails userDetails = platformUserDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    public String getUsernameFromToken(String token) {
        DecodeJwt decodeJwt = DecodeJwt.decode(token.substring("Bearer ".length()));
        return decodeJwt.preferred_username();
    }
}
