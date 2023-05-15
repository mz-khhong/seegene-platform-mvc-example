package com.seegene.mvnpoc.support.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {

  SUCCESS("2000", "OK"),

  /* 1001 ~ 2000 logic error*/
  CODE_NOT_FOUND("1003", "코드를 찾을 수 없음"),
  INVALID_PARAMETER("1004", "유효성 확인 실패"),
  REQUEST_NOT_FOUND("1404", "요청을 찾을 수 없습니다."),

  /* 4000 ~ 5000 Business Error */
  USER_NOT_FOUND("4000", "사용자를 찾을 수 없습니다."),
  USER_PASSWORD_NOT_INPUT("4001", "비밀번호가 입력되지 않았습니다."),
  USER_PASSWORD_UNMATCHED_ERROR("4002", "비밀번호가 일치하지않습니다."),
  USER_PASSWORD_EXPIRED_ERROR("4003", "비밀번호 유효시간이 만료되었습니다."),


  /* 6000 ~ 7000 Token Error */
  TOKEN_VALUE_ERROR("6001", "토큰 값이 누락되었습니다."),
  TOKEN_EXPIRED_DATE_ERROR("6002", "토큰 만료일이 누락되었습니다."),
  TOKEN_EXPIRED_OR_UNUSABLE_ERROR("6003", "토큰의 사용 기한이 만료 되었거나 사용할수 없습니다."),
  TOKEN_UNUSABLE_ERROR("6004", "토큰이 이미 사용되어지거나 재발급되어 사용할수 없습니다."),


  /* 9001 ~  system error*/
  SYSTEM_ERROR("9001", "시스템 오류"),
  BAD_REQUEST_ERROR("9002", "부적절한 요청 오류"),
  UNAUTHORIZED_ERROR("9003", "인증 오류"),
  AUTHORIZATION_KEY_UNMATCHED_ERROR("9004", "인증키가 일치하지 않습니다."),
  AUTHORIZATION_KEY_EXPIRED_ERROR("9005", "인증키 사용 기한이 만료되었습니다."),
  AUTHORIZATION_KEY_NOT_FOUND_ERROR("9006", "인증키가 존재하지 않습니다"),

  NO_SUCH_ENTITY_ERROR("9007", "존재하지 않는 엔티티 오류"),
  EXTERNAL_API_ERROR("9008", "외부 API 호출 에러 입니다."),
  ENTITY_EXISTS_ERROR("9009", "이미 존재하는 데이터입니다."),
  ENTITY_SAVE_ERROR("9010", "데이터 저장에 실패하였습니다."),
  ENTITY_DELETION_ERROR("9011", "데이터 삭제에 실패하였습니다."),

  UNKNOWN_ERROR("9999", "알 수 없는 오류"),
  LOGOUT_ERROR("7979", "로그아웃한 사용자입니다.");

  private final String code;
  private final String defaultMessage;

  ErrorCode(String code, String defaultMessage) {
    this.code = code;
    this.defaultMessage = defaultMessage;
  }

}
