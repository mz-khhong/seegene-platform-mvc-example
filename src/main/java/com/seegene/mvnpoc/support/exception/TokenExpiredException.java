package com.seegene.mvnpoc.support.exception;


import com.seegene.mvnpoc.support.code.ErrorCode;

public class TokenExpiredException extends BusinessException {

  public TokenExpiredException(ErrorCode errorCode) {
    super(errorCode, errorCode.getDefaultMessage());
  }

  public TokenExpiredException(String message) {
    super(message);
  }

  public TokenExpiredException(String message, Throwable cause) {
    super(message, cause);
  }
}
