package com.imti.exception;

/**
 * Created by imteyaz on 04/12/18
 **/

public class TransactionNotFoundException extends RuntimeException {

  public TransactionNotFoundException() {
  }

  public TransactionNotFoundException(String message) {
    super(message);
  }

  public TransactionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public TransactionNotFoundException(Throwable cause) {
    super(cause);
  }

  public TransactionNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
