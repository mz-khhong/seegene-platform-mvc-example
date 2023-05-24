package com.seegene.mvnpoc.web.api.v1.demo.controller;

import com.seegene.mvnpoc.support.dto.ApiResponse;
import com.seegene.mvnpoc.support.dto.ApiResponseGenerator;
import com.seegene.mvnpoc.web.api.v1.demo.domain.dto.UserInfoRecord;
import com.seegene.mvnpoc.web.api.v1.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/anonymous")
    public ApiResponse<String> getAnonymous() {
        return ApiResponseGenerator.success(testService.getAnonymousUser());
    }

    @GetMapping("/signup")
    public @ResponseBody ApiResponse<UserInfoRecord> createUser(@RequestBody UserInfoRecord userInfoRecord) {
        return ApiResponseGenerator.success(testService.createUser(userInfoRecord));
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ApiResponse<String> getAdmin(Authentication authentication) {
        return ApiResponseGenerator.success(testService.getAdminUser(authentication));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public @ResponseBody ApiResponse<String> getUser(Authentication authentication) {
        return ApiResponseGenerator.success(testService.getUser(authentication));

    }

}
