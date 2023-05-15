package com.seegene.mvnpoc.event;

import com.seegene.mvnpoc.support.code.SystemTypeCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ExceptionEvent extends ApplicationEvent {

  private Exception exception;
  private String message;
  private LocalDateTime occurredTime;
  private SystemTypeCode systemTypeCode;

  public ExceptionEvent(Object source) {
    super(source);
  }

  public ExceptionEvent(Object source, String message, SystemTypeCode systemTypeCode) {
    super(source);
    this.message = message;
    this.exception = null;
    this.occurredTime = LocalDateTime.now();
    this.systemTypeCode = systemTypeCode;
  }

  public ExceptionEvent(Object source, Exception exception, SystemTypeCode systemTypeCode) {
    super(source);
    this.message = exception.getMessage();
    this.exception = exception;
    this.occurredTime = LocalDateTime.now();
    this.systemTypeCode = systemTypeCode;
  }

  public ExceptionEvent(Object source, String message, Exception exception,
      SystemTypeCode systemTypeCode) {
    super(source);
    this.message = message;
    this.exception = exception;
    this.occurredTime = LocalDateTime.now();
    this.systemTypeCode = systemTypeCode;
  }

  public ExceptionEvent(Object source, String message, Exception exception,
      LocalDateTime occurredTime, SystemTypeCode systemTypeCode) {
    super(source);
    this.message = message;
    this.exception = exception;
    this.occurredTime = occurredTime;
    this.systemTypeCode = systemTypeCode;
  }
}
