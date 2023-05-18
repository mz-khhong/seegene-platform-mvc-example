//package com.seegene.mvnpoc.web.api.v1.demo.provider;
//
//
//import com.seegene.mvnpoc.web.api.v1.demo.adapter.UserAdapter;
//import com.seegene.mvnpoc.web.api.v1.demo.domain.entity.UserEntity;
//
//
//import com.seegene.mvnpoc.web.api.v1.demo.repository.UserRepository;
//import jakarta.persistence.EntityManager;
//import lombok.extern.slf4j.Slf4j;
//import org.keycloak.component.ComponentModel;
//import org.keycloak.connections.jpa.JpaConnectionProvider;
//import org.keycloak.credential.CredentialInput;
//import org.keycloak.credential.CredentialInputUpdater;
//import org.keycloak.credential.CredentialInputValidator;
//import org.keycloak.models.*;
//import org.keycloak.models.cache.CachedUserModel;
//import org.keycloak.models.cache.OnUserCache;
//import org.keycloak.models.credential.PasswordCredentialModel;
//import org.keycloak.storage.StorageId;
//import org.keycloak.storage.UserStorageProvider;
//import org.keycloak.storage.user.UserLookupProvider;
//import org.keycloak.storage.user.UserQueryProvider;
//import org.keycloak.storage.user.UserRegistrationProvider;
//
//import java.util.*;
//import java.util.stream.Stream;
//
///**
// * class 한글 설명
// * <PRE>
// * </PRE>
// *
// * @author : @mz.co.kr
// * @History <PRE>
// * No  Date           Author            Desc
// * ---- ------------ ---------------- ------------------------------------
// * 1                 최초작성
// * </PRE>
// */
//@Slf4j
//public class PlatformUserStorageProvider implements UserStorageProvider, UserLookupProvider, UserRegistrationProvider,
//        UserQueryProvider, CredentialInputUpdater, CredentialInputValidator, OnUserCache {
//    /**
//     * UserStorageProvider(User Storage SPI), UserLookupProvider(사용자 조회), UserQueryProvider(사용자 조회),UserRegistrationProvider(사용자 등록), CredentialInputValidator(비밀번호 검사), CredentialInputUpdater(비밀번호 변경)
//     */
//
//    protected UserRepository userRepository;
////    protected javax.persistence.EntityManager entityManager;
//    protected ComponentModel componentModel;
//    protected KeycloakSession keycloakSession;
//    public static final String PASSWORD_CACHE_KEY = UserAdapter.class.getName() + ".password";
//
//    PlatformUserStorageProvider(KeycloakSession session, ComponentModel model) {
//        this.keycloakSession = session;
//        this.componentModel = model;
//        entityManager = session.getProvider(JpaConnectionProvider.class, "user-store").getEntityManager();
//    }
//
//    @Override
//    public void preRemove(RealmModel realm) {
//        org.keycloak.storage.UserStorageProvider.super.preRemove(realm);
//    }
//
//    @Override
//    public void preRemove(RealmModel realm, GroupModel group) {
//        org.keycloak.storage.UserStorageProvider.super.preRemove(realm, group);
//    }
//
//    @Override
//    public void preRemove(RealmModel realm, RoleModel role) {
//        org.keycloak.storage.UserStorageProvider.super.preRemove(realm, role);
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//
//    @Override
//    public UserModel getUserById(RealmModel realm, String id) {
//        log.info(String.format("[seegene-platform] userId : %s", id));
//        String persistenceId = StorageId.externalId(id);
//        UserEntity userEntity = entityManager.find(UserEntity.class, persistenceId);
//        if (userEntity == null) {
//            log.error("could not find user by Id : " + id);
//            return null;
//        }
//        return new UserAdapter(keycloakSession, realm, componentModel, userEntity);
//    }
//
//    @Override
//    public UserModel getUserByUsername(RealmModel realm, String username) {
//        log.info(String.format("[seegene-platform] username : %s", username));
//        String getUserByUsername = "getUserByUsername";
//        TypedQuery<UserEntity> query = entityManager.createNamedQuery(getUserByUsername, UserEntity.class);
//        query.setParameter("username", username);
//        List<UserEntity> resultList = query.getResultList();
//        if (resultList.isEmpty()) {
//            log.error("could not find user by Username : " + username);
//        }
//        return new UserAdapter(keycloakSession, realm, componentModel, resultList.get(0));
//    }
//
//    @Override
//    public UserModel getUserByEmail(RealmModel realm, String email) {
//        String getUserByEmail = "getUserByEmail";
//        TypedQuery<UserEntity> query = entityManager.createNamedQuery(getUserByEmail, UserEntity.class);
//        query.setParameter("email", email);
//        List<UserEntity> result = query.getResultList();
//        if (result.isEmpty()) return null;
//        return new UserAdapter(keycloakSession, realm, componentModel, result.get(0));
//    }
//
//    @Override
//    public UserModel addUser(RealmModel realm, String username) {
//        UserEntity entity = new UserEntity();
//        entity.setId(UUID.randomUUID().toString());
//        entity.setUserName(username);
//        entityManager.persist(entity);
//        log.info("added User:" + username);
//        return new UserAdapter(keycloakSession, realm, componentModel, entity);
//    }
//
//    @Override
//    public boolean removeUser(RealmModel realm, UserModel user) {
//        String persistenceId = StorageId.externalId(user.getId());
//        UserEntity entity = entityManager.find(UserEntity.class, persistenceId);
//        if (entity == null) return false;
//        entityManager.remove(entity);
//        return true;
//    }
//
//    @Override
//    public void onCache(RealmModel realm, CachedUserModel user, UserModel delegate) {
//        String password = ((UserAdapter) delegate).getPassword();
//        if (password != null) {
//            user.getCachedWith().put(PASSWORD_CACHE_KEY, password);
//        }
//    }
//
//    @Override
//    public boolean supportsCredentialType(String credentialType) {
//        return PasswordCredentialModel.TYPE.equals(credentialType);
//    }
//
//    @Override
//    public boolean updateCredential(RealmModel realm, UserModel user, CredentialInput input) {
//        if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) return false;
//        UserCredentialModel cred = (UserCredentialModel)input;
//        UserAdapter adapter = getUserAdapter(user);
//        adapter.setPassword(cred.getValue());
//
//        return true;
//    }
//
//    public UserAdapter getUserAdapter(UserModel user) {
//        if (user instanceof CachedUserModel) {
//            return (UserAdapter)((CachedUserModel) user).getDelegateForUpdate();
//        } else {
//            return (UserAdapter) user;
//        }
//    }
//
//    @Override
//    public void disableCredentialType(RealmModel realm, UserModel user, String credentialType) {
//        if (!supportsCredentialType(credentialType)) return;
//
//        getUserAdapter(user).setPassword(null);
//    }
//
//    @Override
//    public Stream<String> getDisableableCredentialTypesStream(RealmModel realm, UserModel user) {
//        if (getUserAdapter(user).getPassword() != null) {
//            Set<String> set = new HashSet<>();
//            set.add(PasswordCredentialModel.TYPE);
//            return set.stream();
//        } else {
//            return Stream.empty();
//        }
//    }
//
//    @Override
//    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
//        return supportsCredentialType(credentialType) && getPassword(user) != null;
//    }
//
//    @Override
//    public boolean isValid(RealmModel realm, UserModel user, CredentialInput input) {
//        if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) return false;
//        UserCredentialModel cred = (UserCredentialModel)input;
//        String password = getPassword(user);
//        return password != null && password.equals(cred.getValue());
//    }
//
//    public String getPassword(UserModel user) {
//        String password = null;
//        if (user instanceof CachedUserModel) {
//            password = (String)((CachedUserModel)user).getCachedWith().get(PASSWORD_CACHE_KEY);
//        } else if (user instanceof UserAdapter) {
//            password = ((UserAdapter)user).getPassword();
//        }
//        return password;
//    }
//
//    @Override
//    public int getUsersCount(RealmModel realm) {
//        String getUserCount = "getUserCount";
//        Object count = entityManager.createNamedQuery(getUserCount)
//                .getSingleResult();
//        return ((Number)count).intValue();
//    }
//
//    @Override
//    public Stream<UserModel> searchForUserStream(RealmModel realm, String search, Integer firstResult, Integer maxResults) {
//        String searchForUser = "searchForUser";
//        TypedQuery<UserEntity> query = entityManager.createNamedQuery(searchForUser, UserEntity.class);
//        query.setParameter("search", "%" + search.toLowerCase() + "%");
//        if (firstResult != -1) {
//            query.setFirstResult(firstResult);
//        }
//        if (maxResults != -1) {
//            query.setMaxResults(maxResults);
//        }
//        List<UserEntity> results = query.getResultList();
//        List<UserModel> users = new LinkedList<>();
//        for (UserEntity entity : results) users.add(new UserAdapter(keycloakSession, realm, componentModel, entity));
//        return users.stream();
//    }
//
//    @Override
//    public Stream<UserModel> searchForUserStream(RealmModel realm, Map<String, String> params, Integer firstResult, Integer maxResults) {
//        return Stream.empty();
//    }
//
//    @Override
//    public Stream<UserModel> getGroupMembersStream(RealmModel realm, GroupModel group, Integer firstResult, Integer maxResults) {
//        return Stream.empty();
//    }
//
//    @Override
//    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realm, String attrName, String attrValue) {
//        return Stream.empty();
//    }
//}
