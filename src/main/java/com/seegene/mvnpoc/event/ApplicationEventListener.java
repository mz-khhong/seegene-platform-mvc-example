package com.seegene.mvnpoc.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ApplicationEventListener {

  @Async
  @EventListener
  public void exceptionEventListener(ExceptionEvent event) {
    log.error("ApplicationEventListener > exceptionEventListener > event:{}", event);


    // error message Send
  }

  @Async
  @EventListener
  public void transactionEventListener(final TransactionEvent event) {
    log.error("ApplicationEventListener > transactionEventListener > event:{}", event);

    // error message Send

  }
}
