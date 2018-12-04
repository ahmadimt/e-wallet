package com.imti.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.imti.persistence.model.Wallet;
import com.imti.util.WalletType;
import java.math.BigDecimal;

/**
 * Created by imteyaz on 01/12/18
 **/
@JsonInclude(Include.NON_NULL)
public class WalletDto {

  private Long id;

  private String name;

  private WalletType type;

  private BigDecimal balance;

  public WalletDto() {
  }

  public WalletDto(Long id, String name, WalletType type, BigDecimal balance) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.balance = balance;
  }

  private WalletDto(String name, WalletType type, BigDecimal balance) {
    this.name = name;
    this.type = type;
    this.balance = balance;
  }

  public WalletDto(Long id, WalletType type, BigDecimal balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public static WalletDto from(Wallet wallet) {
    return new WalletDto(wallet.getName(), wallet.getType(), wallet.getBalance());
  }


  public Long getId() {
    return id;
  }

  public WalletType getType() {
    return type;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "WalletDto{" +
        "name='" + name + '\'' +
        ", type=" + type +
        ", balance=" + balance +
        '}';
  }
}
