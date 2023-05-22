package com.seegene.mvnpoc.web.api.v1.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


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
public class UserEntity {

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
}
