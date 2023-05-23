package com.seegene.mvnpoc.web.api.v1.demo.domain.entity;

import com.seegene.mvnpoc.support.code.RoleCode;
import com.seegene.mvnpoc.support.code.WorkCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


/**
 * class 한글 설명
 * <PRE>
 * </PRE>
 *
 * @author : khhong@mz.co.kr
 * @History <PRE>
 * No  Date           Author            Desc
 * ---- ------------ ---------------- ------------------------------------
 * 1 2023-05-17        khhong        최초작성
 * </PRE>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable, UserDetails {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /** 아이디 */
    @Column(name = "userId", nullable = false)
    private String userId;

    /** 이름 */
    @Column(name = "username", nullable = false)
    private String userName;

    /** 비밀번호 */
    @Column(name = "userpassword", length = 200)
    private String userPassword;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "fullName", nullable = false)
    private String fullName;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "hashPwd", nullable = false)
    private String hashPwd;

    /** 재직상태(재직/휴직/퇴직) */
    @Column(name = "user_work_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkCode userWorkStatus;

    /** 권한 */
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private RoleCode userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.getRoleName()));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
