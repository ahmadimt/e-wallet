package com.imti.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imteyaz on 04/12/18
 **/

public class WalletResponse {

  private List<WalletDto> wallets;

  private String message;

  public WalletResponse(List<WalletDto> wallets, String message) {
    this.wallets = wallets;
    this.message = message;
  }

  public List<WalletDto> getWallets() {
    return wallets;
  }

  public void setWallets(List<WalletDto> wallets) {
    this.wallets = wallets;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "WalletResponse{" +
        "wallets=" + wallets +
        ", message='" + message + '\'' +
        '}';
  }
}
