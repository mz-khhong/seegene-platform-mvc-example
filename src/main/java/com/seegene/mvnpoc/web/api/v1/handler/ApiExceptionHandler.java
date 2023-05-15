package com.seegene.mvnpoc.web.api.v1.handler;

import com.seegene.mvnpoc.event.TransactionEvent;
import com.seegene.mvnpoc.support.code.ErrorCode;
import com.seegene.mvnpoc.support.code.SystemTypeCode;
import com.seegene.mvnpoc.support.dto.ApiResponse;
import com.seegene.mvnpoc.support.dto.ApiResponseGenerator;
import com.seegene.mvnpoc.support.dto.InvalidArguments;
import com.seegene.mvnpoc.support.exception.*;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

import static com.seegene.mvnpoc.support.code.ErrorCode.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

  private static final String INVALID_EXCEPTION_MESSAGE = "ApiExceptionHandler > Invalidation Exception > errorMessage:{}";

  private final ApplicationEventPublisher applicationEventPublisher;

  /**
   * Business Exception
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(BusinessException.class)
  public ApiResponse<Void> handleWaning(BusinessException e) {
    return handleBusinessException(e, false);
  }

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({
      InvalidValueException.class,
      InvalidStateException.class,
      EntityNotFoundException.class,
      EntityExistsException.class,
      EntitySaveFailedException.class,
      EntityDeletionFailedException.class
  })
  public ApiResponse<Void> handleDataException(BusinessException e) {
    return handleBusinessException(e, false);
  }

  private ApiResponse<Void> handleBusinessException(BusinessException e, boolean isDataError) {
    String errorMessage = isDataError ? "ApiExceptionHandler > InvalidDataException > exception: {}, {}": "ApiExceptionHandler > BusinessException > exception: {}, {}";
    log.error(errorMessage, e.getMessage(), e);

    if(isDataError) this.publishTransactionEvent(e);

    ErrorCode ec = e.getErrorCode() == null ? BAD_REQUEST_ERROR : e.getErrorCode();
    return ApiResponseGenerator.fail(ec, e.getMessage());
  }

  /**
   * hibernate validator - No alarm
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ApiResponse<List<InvalidArguments>> handleValidation(MethodArgumentNotValidException e) {
    log.error(INVALID_EXCEPTION_MESSAGE, e.getMessage(), e);
    return ApiResponseGenerator.fail(INVALID_PARAMETER, e.getBindingResult().getFieldErrors().stream()
        .map(InvalidArguments::new)
        .collect(Collectors.toList()));
  }

  /**
   * get method hibernate validator - No alarm
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({BindException.class})
  public ApiResponse<List<InvalidArguments>> handleValidation(BindException e) {
    log.error(INVALID_EXCEPTION_MESSAGE, e.getMessage(), e);
    return ApiResponseGenerator.fail(INVALID_PARAMETER,
        e.getBindingResult().getFieldErrors().stream()
            .map(InvalidArguments::new)
            .collect(Collectors.toList()));
  }

  /**
   * ConstraintViolationException - No alarm
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({ConstraintViolationException.class})
  public ApiResponse<Void> handleValidation(ConstraintViolationException e) {
    log.error(INVALID_EXCEPTION_MESSAGE, e.getMessage(), e);
    return ApiResponseGenerator.fail(INVALID_PARAMETER, e.getMessage());
  }

  /**
   * spring security AccessDenied - No alarm
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({AccessDeniedException.class})
  public ApiResponse<Void> handleValidation(AccessDeniedException e) {
    log.error("ApiExceptionHandler > AccessDenied Exception > errorMessage: {}", e.getMessage(), e);
    return ApiResponseGenerator.fail(UNAUTHORIZED_ERROR, e.getMessage());
  }

  /**
   * Broken Pipe - No alarm
   */
  @ExceptionHandler({ClientAbortException.class})
  public void handleBrokenPipe(ClientAbortException e) {
    log.warn("ApiExceptionHandler > ClientAbortException > errorMessage: {}", e.getMessage(), e);
  }

  /**
   * TokenExpiredException - No alarm
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(TokenExpiredException.class)
  public ApiResponse<Void> handleTokenExpiredException(TokenExpiredException e) {
    return ApiResponseGenerator.fail(e.getErrorCode(), e.getMessage());
  }

  /**
   * Bad Request related - No alarm
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({
      HttpMessageNotReadableException.class,
      HttpRequestMethodNotSupportedException.class,
      MethodArgumentTypeMismatchException.class
  })
  public ApiResponse<Void> handleBadRequest() {
    return ApiResponseGenerator.fail(BAD_REQUEST_ERROR);
  }

  /**
   * RuntimeException - No alarm
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(RuntimeException.class)
  public ApiResponse<Void> handleRuntimeException(RuntimeException e) {
    log.error("ApiExceptionHandler > RuntimeException > errorMessage: {}", e.getMessage(), e);
    return ApiResponseGenerator.fail(SYSTEM_ERROR);
  }

  /**
   * UNKNOWN_ERROR
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(Exception.class)
  public ApiResponse<Void> handleException(Exception e) {
    log.error("ApiExceptionHandler > Exception > errorMessage:{}", e.getMessage(), e);
    this.publishTransactionEvent(e);
    return ApiResponseGenerator.fail(UNKNOWN_ERROR);
  }

  /**
   * Transaction Exception Event Publisher
   * @param e
   */
  private void publishTransactionEvent(Exception e) {
    final var transactionEvent = new TransactionEvent(this, ExceptionUtils.getStackTrace(e), e, SystemTypeCode.SYSTY000);
    applicationEventPublisher.publishEvent(transactionEvent);
  }
}
