package com.seegene.mvnpoc.web.api.v1.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * class 한글 설명
 * <PRE>
 * </PRE>
 *
 * @author : @mz.co.kr
 * @History <PRE>
 * No  Date           Author            Desc
 * ---- ------------ ---------------- ------------------------------------
 * 1                 최초작성
 * </PRE>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiTokenRecord(String accessToken, String issueAt) {
}
