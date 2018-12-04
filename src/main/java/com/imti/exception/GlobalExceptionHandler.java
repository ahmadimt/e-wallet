package com.imti.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by imteyaz on 04/12/18
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = WalletNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiErrorResponse handleWalletNotFoundException(final WalletNotFoundException wnfe) {
    LOGGER.error("Requested resource not found {}", wnfe.getMessage());
    return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), wnfe.getMessage());
  }

  @ExceptionHandler(value = TransactionNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiErrorResponse handleTransactionNotFoundException(
      final TransactionNotFoundException tnfe) {
    LOGGER.error("Requested resource not found {}", tnfe.getMessage());
    return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), tnfe.getMessage());
  }

}
