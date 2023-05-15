package com.seegene.mvnpoc.support.exception;

import com.seegene.mvnpoc.support.code.ErrorCode;

public class EntityDeletionFailedException extends BusinessException {

  public EntityDeletionFailedException() {
    super(ErrorCode.ENTITY_DELETION_ERROR);
  }

  public EntityDeletionFailedException(ErrorCode errorCode) {
    super(errorCode);
  }

  public EntityDeletionFailedException(String message) {
    super(message);
  }

  public EntityDeletionFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
