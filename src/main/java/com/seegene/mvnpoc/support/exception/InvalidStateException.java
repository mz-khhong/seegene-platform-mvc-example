package com.seegene.mvnpoc.support.exception;

import com.seegene.mvnpoc.support.code.ErrorCode;

public class InvalidStateException extends BusinessException {

  public InvalidStateException(ErrorCode errorCode) {
    super(errorCode);
  }

  public InvalidStateException(String message) {
    super(message);
  }

  public InvalidStateException(String message, Throwable cause) {
    super(message, cause);
  }
}
