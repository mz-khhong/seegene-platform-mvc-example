package com.seegene.mvnpoc.support.dto;

import com.seegene.mvnpoc.support.code.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse<T> {

  private String code;

  private String message;

  private T data;

  ApiResponse(ErrorCode errorCode) {
    this(errorCode, null, null);
  }

  ApiResponse(ErrorCode errorCode, String code) {
    this.code = errorCode.toString();
    this.message = code;
  }

  ApiResponse(ErrorCode errorCode, T data) {
    this.code = errorCode.getCode();
    this.message = errorCode.getDefaultMessage();
    this.data = data;
  }

  ApiResponse(ErrorCode errorCode, String responseMessage, T data) {
    this.code = errorCode.getCode();
    this.message = (responseMessage == null ? errorCode.getDefaultMessage() : responseMessage);
    this.data = data;
  }

  public T getData() {
    return data;
  }
}
