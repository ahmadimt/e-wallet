package com.imti.persistence.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.imti.dto.WalletDto;
import com.imti.util.WalletType;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 * Created by imteyaz on 01/12/18
 **/
@Entity
@JsonInclude(Include.NON_NULL)
public class Wallet extends AbstractAuditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private WalletType type;

  private BigDecimal balance = BigDecimal.ZERO;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(inverseJoinColumns = @JoinColumn(name = "transaction_id"))
  private Set<Transaction> transactions = new HashSet<>();

  public Wallet() {
  }

  public Wallet(String name, WalletType type) {
    this.name = name;
    this.type = type;
  }

  public Wallet(WalletType type, BigDecimal balance) {
    this.type = type;
    this.balance = balance;
  }

  public Wallet(String name, WalletType type, BigDecimal balance) {
    this.name = name;
    this.type = type;
    this.balance = balance;
  }

  public static Wallet from(WalletDto walletDto) {

    return new Wallet(walletDto.getName(), walletDto.getType());
  }

  public static WalletDto to(Wallet wallet) {
    return new WalletDto(wallet.getId(),wallet.getType(), wallet.getBalance());
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WalletType getType() {
    return type;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Set<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(Set<Transaction> transactions) {
    this.transactions = transactions;
  }

  @Override
  public String toString() {
    return "Wallet{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", balance=" + balance +
        '}';
  }
}

