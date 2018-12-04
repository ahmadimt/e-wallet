package com.imti.controller;

import com.imti.dto.TransactionDto;
import com.imti.dto.TransactionResponse;
import com.imti.service.WalletService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by imteyaz on 01/12/18
 **/
@RestController
@RequestMapping(value = WalletResources.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletResources {

  static final String BASE_URL = "/api/v1/wallet";

  private final WalletService walletService;

  @Autowired
  public WalletResources(WalletService walletService) {
    this.walletService = walletService;
  }

  @GetMapping(value = "/{wallet_id}")
  public ResponseEntity getWalletDetails(@PathVariable("wallet_id") Long walletId) {
    return ResponseEntity.ok().body(walletService.getWalletDetailsById(walletId));
  }

  @PostMapping(value = "/{wallet_id}/transaction")
  public ResponseEntity createWalletTransaction(@PathVariable("wallet_id") Long walletId,
      @RequestBody TransactionDto transactionDto) {
    return ResponseEntity.ok().body(walletService.performTransaction(walletId, transactionDto));
  }

  @GetMapping(value = "/{wallet_id}/transaction")
  public ResponseEntity getWalletTransactions(@PathVariable("wallet_id") Long walletId) {
    return ResponseEntity.ok()
        .body(new TransactionResponse(walletService.listTransactions(walletId), "OK"));
  }

  @DeleteMapping(value = "/{wallet_id}/transaction/{transaction_id}")
  public ResponseEntity deleteTransactions(@PathVariable("wallet_id") Long walletId,
      @PathVariable("transaction_id")
          UUID transactionId) {
    return ResponseEntity.ok().body(walletService.deleteTransaction(walletId, transactionId));
  }
}
