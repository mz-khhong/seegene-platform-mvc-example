package com.seegene.mvnpoc.web.api.v1.demo.repository;

import com.seegene.mvnpoc.web.api.v1.demo.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Object> {

    Optional<UserEntity> findByUserName(String username);
    Optional<UserEntity> findByUserId(String userId);

}
