package com.seegene.mvnpoc.support.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RoleCode {
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN"),
    ETC("ETC");
    private final String roleName;

    RoleCode(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public static String getRoleCode(RoleCode roleCode) {
        switch (roleCode){
            case USER:
                return USER.roleName;
            case MANAGER:
                return MANAGER.roleName;
            case ADMIN:
                return ADMIN.roleName;
            case ETC:
                return ETC.roleName;
        }
        return USER.roleName;
    }
}
