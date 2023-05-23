package com.seegene.mvnpoc.web.api.v1.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.annotation.Validated;

/**
 * class 한글 설명
 * <PRE>
 * </PRE>
 *
 * @author : khhong@mz.co.kr
 * @History <PRE>
 * No  Date           Author            Desc
 * ---- ------------ ---------------- ------------------------------------
 * 1 2023-05-11        khhong        최초작성
 * </PRE>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public record ToKenResponseRecord(String accessToken, String refreshToken, String accessCreateDt, String accessExpireDt, String refreshCreateDt, String refreshExpireDt) {
}
