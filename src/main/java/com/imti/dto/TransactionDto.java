package com.imti.dto;

import com.imti.persistence.helper.Status;
import com.imti.persistence.helper.TransactionType;
import com.imti.persistence.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by imteyaz on 03/12/18
 **/

public class TransactionDto {

  private UUID transactionId;

  private BigDecimal amount;

  private TransactionType type;

  private Status status;

  private LocalDateTime timestamp;

  public TransactionDto() {
  }

  public TransactionDto(BigDecimal amount,
      TransactionType type) {
    this.amount = amount;
    this.type = type;
  }

  private TransactionDto(UUID transactionId,
      TransactionType type, BigDecimal amount, Status status) {
    this.transactionId = transactionId;
    this.amount = amount;
    this.type = type;
    this.status = status;
  }

  public TransactionDto(UUID transactionId, TransactionType type, BigDecimal amount, Status status,
      LocalDateTime timestamp) {
    this.transactionId = transactionId;
    this.type = type;
    this.amount = amount;
    this.status = status;
    this.timestamp = timestamp;
  }

  public static TransactionDto from(Transaction transaction) {
    return new TransactionDto(transaction.getTransactionId(),
        transaction.getType(),
        transaction.getAmount(),
        transaction.getStatus());
  }

  public static TransactionDto fromTransaction(Transaction transaction) {
    return new TransactionDto(transaction.getTransactionId(), transaction.getType(),
        transaction.getAmount(),
        transaction.getStatus(), transaction.getModifiedDate());
  }

  public UUID getTransactionId() {
    return transactionId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public TransactionType getType() {
    return type;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "TransactionDto{" +
        "transactionId=" + transactionId +
        ", amount=" + amount +
        ", type=" + type +
        ", status=" + status +
        ", timestamp=" + timestamp +
        '}';
  }
}
