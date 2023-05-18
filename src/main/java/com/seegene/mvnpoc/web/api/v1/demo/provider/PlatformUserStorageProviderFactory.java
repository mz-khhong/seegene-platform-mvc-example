//package com.seegene.mvnpoc.web.api.v1.demo.provider;
//
//import lombok.extern.slf4j.Slf4j;
//import org.keycloak.Config;
//import org.keycloak.component.ComponentModel;
//import org.keycloak.models.KeycloakSession;
//import org.keycloak.storage.UserStorageProviderFactory;
//
///**
// * class 한글 설명
// * <PRE>
// * </PRE>
// *
// * @author : khhong@mz.co.kr
// * @History <PRE>
// * No  Date           Author            Desc
// * ---- ------------ ---------------- ------------------------------------
// * 1 2023-05-17        khhong        최초작성
// * </PRE>
// */
//@Slf4j
//public class PlatformUserStorageProviderFactory implements UserStorageProviderFactory<PlatformUserStorageProvider> {
//
//    public static final String PROVIDER_ID = "example-user-storage-jpa";
//
//    @Override
//    public void init(Config.Scope config) {
//        UserStorageProviderFactory.super.init(config);
//    }
//
//    @Override
//    public PlatformUserStorageProvider create(KeycloakSession session, ComponentModel model) {
//        return new PlatformUserStorageProvider(session, model);
//    }
//
//    @Override
//    public String getId() {
//        return PROVIDER_ID;
//    }
//
//    @Override
//    public String getHelpText() {
//        return "JPA Example User Storage Provider";
//    }
//
//    @Override
//    public void close() {
//        log.info("<<<<<< Closing factory");
//    }
//}
