package com.seegene.mvnpoc.web.api.v1.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
//@NamedQueries({
//        @NamedQuery(name="getUserByUsername", query="select u from UserEntity u where u.username = :username"),
//        @NamedQuery(name="getUserByEmail", query="select u from UserEntity u where u.email = :email"),
//        @NamedQuery(name="getUserCount", query="select count(u) from UserEntity u"),
//        @NamedQuery(name="getAllUsers", query="select u from UserEntity u"),
//        @NamedQuery(name="searchForUser", query="select u from UserEntity u where " +
//                "( lower(u.username) like :search or u.email like :search ) order by u.username"),
//})
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /** 아이디 */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /** 이름 */
    @Column(name = "user_name", nullable = false)
    private String userName;

    /** 비밀번호 */
    @Column(name = "user_password", length = 200)
    private String userPassword;

    private String email;

    private String phone;
}
