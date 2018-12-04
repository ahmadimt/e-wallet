package com.imti.exception;

/**
 * Created by imteyaz on 04/12/18
 **/

public class ApiErrorResponse {

  private int status;

  private String message;

  public ApiErrorResponse() {
  }

  public ApiErrorResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "ApiErrorResponse{" +
        "status=" + status +
        ", message=" + message +
        '}';
  }
}
