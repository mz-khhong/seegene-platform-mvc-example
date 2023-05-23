package com.seegene.mvnpoc.web.api.v1.demo.service;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
@Service
public class TestService {

    public String getAnonymousUser() {
        return String.format("[Seegene-platform-mvc-example]\n Hello Anonymous");
    }

    public String getAdminUser(Authentication authentication) {
        log.info(String.format("[getUser] user: %s, role:%s, ", authentication.getName(), authentication.getAuthorities().toString()));
        String name = authentication.getName();
        String role = authentication.getAuthorities().toString();
        return String.format("[Seegene-platform-mvc-example] Hello Admin: %s, Role: %s", name, role);
    }

    public String getUser(Authentication authentication) {
        log.info(String.format("[getUser] user: %s, role:%s, ", authentication.getName(), authentication.getAuthorities().toString()));
        String name = authentication.getName();
        String role = authentication.getAuthorities().toString();
        return String.format("[Seegene-platform-mvc-example] Hello User: %s, Role: %s", name, role);
    }
}
