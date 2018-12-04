package com.imti.exception;

/**
 * Created by imteyaz on 04/12/18
 **/

public class WalletNotFoundException extends RuntimeException {

  public WalletNotFoundException() {
  }

  public WalletNotFoundException(String message) {
    super(message);
  }

  public WalletNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public WalletNotFoundException(Throwable cause) {
    super(cause);
  }

  public WalletNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
