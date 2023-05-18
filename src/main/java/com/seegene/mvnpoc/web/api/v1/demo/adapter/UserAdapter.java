//package com.seegene.mvnpoc.web.api.v1.demo.adapter;
//
//import com.seegene.mvnpoc.web.api.v1.demo.domain.entity.UserEntity;
//import org.keycloak.component.ComponentModel;
//import org.keycloak.models.KeycloakSession;
//import org.keycloak.models.RealmModel;
//import org.keycloak.storage.StorageId;
//import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;
//
//import javax.ws.rs.core.MultivaluedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Stream;
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
//public class UserAdapter extends AbstractUserAdapterFederatedStorage {
//
//    protected UserEntity userEntity;
//    protected String keycloakId;
//
//    public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, UserEntity userEntity) {
//        super(session, realm, model);
//        this.userEntity = userEntity;
//        keycloakId = StorageId.keycloakId(model, userEntity.getId());
//    }
//    public String getPassword() {
//        return userEntity.getUserPassword();
//    }
//
//    public void setPassword(String password) {
//        userEntity.setUserPassword(password);
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public void setUsername(String username) {
//
//    }
//
//    @Override
//    public void setEmail(String email) {
//        super.setEmail(email);
//    }
//    @Override
//    public String getEmail() {
//        return super.getEmail();
//    }
//
//    @Override
//    public String getId() {
//        return super.getId();
//    }
//
//    @Override
//    public void setSingleAttribute(String name, String value) {
//        if (name.equals("phone")) {
//            userEntity.setPhone(value);
//        } else {
//            super.setSingleAttribute(name, value);
//        }
//    }
//
//    @Override
//    public void removeAttribute(String name) {
//        if (name.equals("phone")) {
//            userEntity.setPhone(null);
//        } else {
//            super.removeAttribute(name);
//        }
//    }
//
//    @Override
//    public void setAttribute(String name, List<String> values) {
//        if (name.equals("phone")) {
//            userEntity.setPhone(values.get(0));
//        } else {
//            super.setAttribute(name, values);
//        }
//    }
//
//    @Override
//    public String getFirstAttribute(String name) {
//        if (name.equals("phone")) {
//            return userEntity.getPhone();
//        } else {
//            return super.getFirstAttribute(name);
//        }
//    }
//
//    @Override
//    public Map<String, List<String>> getAttributes() {
//        Map<String, List<String>> attrs = super.getAttributes();
//        MultivaluedHashMap<String, String> all = new MultivaluedHashMap<>();
//        all.putAll(attrs);
//        all.add("phone", userEntity.getPhone());
//        return all;
//    }
//
//    @Override
//    public Stream<String> getAttributeStream(String name) {
//
//        if (name.equals("phone")) {
//            List<String> phone = new LinkedList<>();
//            phone.add(userEntity.getPhone());
//            return phone.stream();
//        } else {
//            return super.getAttributeStream(name);
//        }
//    }
//}
