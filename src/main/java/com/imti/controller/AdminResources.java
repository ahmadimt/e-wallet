package com.imti.controller;

import com.imti.dto.WalletDto;
import com.imti.dto.WalletResponse;
import com.imti.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by imteyaz on 01/12/18
 **/

@RestController
@RequestMapping(value = AdminResources.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminResources {

  static final String BASE_URL = "/api/v1/admin/wallet/";

  private final WalletService walletService;

  @Autowired
  public AdminResources(WalletService walletService) {
    this.walletService = walletService;
  }

  @GetMapping
  public ResponseEntity<WalletResponse> listAllWallets() {
    return ResponseEntity.ok(new WalletResponse(walletService.getAllWallets(), "OK"));
  }

  @PostMapping
  public ResponseEntity createWallet(@RequestBody WalletDto walletDto) {
    return ResponseEntity.ok().body(walletService.createWallet(walletDto));
  }


}
