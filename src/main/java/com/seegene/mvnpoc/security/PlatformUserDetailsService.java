package com.seegene.mvnpoc.security;

import com.seegene.mvnpoc.support.exception.EntityNotFoundException;
import com.seegene.mvnpoc.web.api.v1.demo.domain.entity.UserEntity;
import com.seegene.mvnpoc.web.api.v1.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * class 한글 설명
 * <PRE>
 * </PRE>
 *
 * @author : khhong@mz.co.kr
 * @History <PRE>
 * No  Date           Author            Desc
 * ---- ------------ ---------------- ------------------------------------
 * 1 2023-05-23        khhong        최초작성
 * </PRE>
 */
@Component
@RequiredArgsConstructor
public class PlatformUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(() -> new EntityNotFoundException());
    }
}
