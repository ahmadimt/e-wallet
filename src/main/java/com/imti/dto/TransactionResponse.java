package com.imti.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imteyaz on 04/12/18
 **/

public class TransactionResponse {

  private List<TransactionDto> transactions = new ArrayList<>();

  private String message;

  public TransactionResponse() {
  }

  public TransactionResponse(List<TransactionDto> transactions, String message) {
    this.transactions = transactions;
    this.message = message;
  }

  public List<TransactionDto> getTransactions() {
    return transactions;
  }

  public String getMessage() {
    return message;
  }
}
