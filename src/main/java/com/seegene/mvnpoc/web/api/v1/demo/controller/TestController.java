package com.seegene.mvnpoc.web.api.v1.demo.controller;

import com.seegene.mvnpoc.support.dto.ApiResponse;
import com.seegene.mvnpoc.support.dto.ApiResponseGenerator;
import com.seegene.mvnpoc.web.api.v1.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/anonymous")
    public ApiResponse<String> getAnonymous() {
        return ApiResponseGenerator.success(testService.getAnonymousUser());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse<String> getAdmin(Principal principal) {
        return ApiResponseGenerator.success(testService.getAdminUser(principal));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public  @ResponseBody ApiResponse<String> getUser(Principal principal) {
        return ApiResponseGenerator.success(testService.getUser(principal));

    }

    // to-be
    @GetMapping("/sqs-test")
    public  @ResponseBody ApiResponse<String> getSqsTest(Principal principal) {
        return ApiResponseGenerator.success(testService.getUser(principal));
    }
}
