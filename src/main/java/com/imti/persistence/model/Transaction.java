package com.imti.persistence.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.imti.dto.TransactionDto;
import com.imti.persistence.helper.Status;
import com.imti.persistence.helper.TransactionType;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by imteyaz on 01/12/18
 **/
@Entity
@Table(name = "transaction")
@JsonInclude(Include.NON_NULL)
public class Transaction extends AbstractAuditable {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID transactionId;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  private Status status = Status.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id", referencedColumnName = "id")
  private Wallet wallet;

  public Transaction() {
  }

  private Transaction(TransactionType type, BigDecimal amount, Wallet wallet) {
    this.type = type;
    this.amount = amount;
    this.wallet = wallet;
  }

  public static Transaction from(TransactionDto transactionDto, Wallet walletId) {
    return new Transaction(transactionDto.getType(), transactionDto.getAmount(),
        walletId);
  }

  public UUID getTransactionId() {
    return transactionId;
  }

  public TransactionType getType() {
    return type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Wallet getWallet() {
    return wallet;
  }

  public void setWallet(Wallet wallet) {
    this.wallet = wallet;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "transactionId=" + transactionId +
        ", type=" + type +
        ", amount=" + amount +
        ", status=" + status +
        ", wallet=" + wallet +
        '}';
  }
}
