package com.seegene.mvnpoc.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * class 한글 설명
 * <PRE>
 * </PRE>
 *
 * @author : khhong@mz.co.kr
 * @History <PRE>
 * No  Date           Author            Desc
 * ---- ------------ ---------------- ------------------------------------
 * 1 2023-05-23        khhong        최초작성
 * </PRE>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
//    private final PlatformUserDetailsService platformUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader(AUTHORIZATION);
            if(StringUtils.isEmpty(authHeader)){
                log.info("authorization is Empty");
            }

            String usernameFromToken = jwtProvider.getUsernameFromToken(authHeader);
            Authentication authenticate = jwtProvider.authenticate(new UsernamePasswordAuthenticationToken(usernameFromToken, ""));
    //        ToKenResponseRecord toKenResponseRecord = jwtProvider.changeToken(authHeader);
    //        Authentication authentication = jwtProvider.getAuthentication(toKenResponseRecord.accessToken());
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
