package com.seegene.mvnpoc.support.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RoleCode {
    ROLE_USER("ROLE_USER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String roleName;

    RoleCode(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public static String getRoleCode(RoleCode roleCode) {
        switch (roleCode){
            case ROLE_USER:
                return ROLE_USER.roleName;
            case ROLE_MANAGER:
                return ROLE_MANAGER.roleName;
            case ROLE_ADMIN:
                return ROLE_ADMIN.roleName;
        }
        return ROLE_USER.roleName;
    }
}
