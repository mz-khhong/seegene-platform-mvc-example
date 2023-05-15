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
public class TransactionEvent extends ApplicationEvent {

  private String message;
  private Exception exception;
  private SystemTypeCode systemTypeCode;
  private LocalDateTime occurredTime;

  public TransactionEvent(Object source) {
    super(source);
  }

  public TransactionEvent(Object source, String message, Exception exception,
      SystemTypeCode systemTypeCode) {
    super(source);
    this.message = message;
    this.exception = exception;
    this.systemTypeCode = systemTypeCode;
    this.occurredTime = LocalDateTime.now();
  }
}
