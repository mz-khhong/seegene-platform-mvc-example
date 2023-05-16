package com.seegene.mvnpoc.web.api.v1.demo.service;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
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
@Service
public class TestService {

    public String getAnonymousUser() {
        return String.format("[Seegene-platform-mvc-example]\n Hello Anonymous");
    }

    public String getAdminUser(Principal principal) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userName = (String) token.getTokenAttributes().get("name");
        String userEmail = (String) token.getTokenAttributes().get("email");
        String role = (String) token.getTokenAttributes().get("roles");
        return String.format("[Seegene-platform-mvc-example]\n Hello Admin \nUser Name : %s\nUser Email : %s,\n Role:%s",userName, userEmail, role);
    }

    public String getUser(Principal principal) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String tokenValue = token.getToken().getTokenValue();
        String userName = (String) token.getTokenAttributes().get("name");
        String userEmail = (String) token.getTokenAttributes().get("email");
        LinkedTreeMap realm_access = (LinkedTreeMap) token.getTokenAttributes().get("realm_access");
        ArrayList roles = (ArrayList) realm_access.get("roles");
        Object collect = roles.stream().collect(Collectors.toList());

        return String.format("[Seegene-platform-mvc-example]\n Hello User \nUser Name : %s\nUser Email : %s, roles:%s",userName, userEmail, collect);
    }
}
