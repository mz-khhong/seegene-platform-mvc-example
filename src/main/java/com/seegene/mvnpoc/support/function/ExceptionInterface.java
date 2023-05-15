package com.seegene.mvnpoc.support.function;

import com.seegene.mvnpoc.support.code.ErrorCode;
import com.seegene.mvnpoc.support.exception.BusinessException;
import com.seegene.mvnpoc.support.exception.TokenExpiredException;
import jakarta.persistence.EntityNotFoundException;

import java.util.function.Consumer;

public interface ExceptionInterface {

  Consumer<ErrorCode> businessException = errorCode -> {
    throw new BusinessException(errorCode, errorCode.getDefaultMessage());
  };

  Runnable entityNotFoundException = () -> {
    throw new EntityNotFoundException(ErrorCode.NO_SUCH_ENTITY_ERROR.getDefaultMessage());
  };

  Runnable tokenExpiredException = () -> {
    throw new TokenExpiredException(ErrorCode.TOKEN_EXPIRED_OR_UNUSABLE_ERROR);
  };

  Runnable codeNotFoundException = () -> {
    throw new RuntimeException(ErrorCode.CODE_NOT_FOUND.getDefaultMessage());
  };
}
