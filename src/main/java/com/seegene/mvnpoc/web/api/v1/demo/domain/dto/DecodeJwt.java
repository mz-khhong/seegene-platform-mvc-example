package com.seegene.mvnpoc.web.api.v1.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

import java.util.Base64;

/**
 * class 한글 설명
 * <PRE>
 * </PRE>
 *
 * @author : khhong@mz.co.kr
 * @History <PRE>
 * No  Date           Author            Desc
 * ---- ------------ ---------------- ------------------------------------
 * 1 2023-05-22        khhong        최초작성
 * </PRE>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record DecodeJwt(String exp, String iat, String jti, String iss, String aud, String sub, String typ, String azp, String session_state,
                        String acr, Object realm_access, Object resource_access, String scope, String sid, String email_verified, String name,
                        String preferred_username, String given_name, String family_name, String email) {

    public static DecodeJwt decode(String token) {
        String[] pieces = token.split("\\.");
        String b64payload = pieces[1];
        String decodedPayload = new String(Base64.getDecoder().decode(b64payload));
        return new Gson().fromJson(decodedPayload, DecodeJwt.class);
    }
}
    /** {
     "exp": 1684802465,
     "iat": 1684802165,
     "jti": "4d62620c-b5ce-48b3-9469-9ce14b2bc357",
     "iss": "http://localhost:9080/realms/seegene-platform",
     "aud": "account",
     "sub": "f:5c3fbb31-97fb-4dbb-b444-a77926de05cd:39cb8467-f877-11ed-b727-0242c0a86002",
     "typ": "Bearer",
     "azp": "platform-client",
     "session_state": "aeecea84-ccbc-470b-8c5c-30a5fbb599ec",
     "acr": "1",
     "realm_access": {
     "roles": [
     "offline_access",
     "uma_authorization"
     ]
     },
     "resource_access": {
     "account": {
     "roles": [
     "manage-account",
     "manage-account-links",
     "view-profile"
     ]
     }
     },
     "scope": "email profile",
     "sid": "aeecea84-ccbc-470b-8c5c-30a5fbb599ec",
     "email_verified": false,
     "name": "kkk sdad",
     "preferred_username": "master",
     "given_name": "kkk",
     "family_name": "sdad",
     "email": "kk@naver.com"
     }
}
 */
