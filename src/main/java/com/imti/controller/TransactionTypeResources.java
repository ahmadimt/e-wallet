package com.imti.controller;

import com.imti.persistence.helper.TransactionType;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by imteyaz on 04/12/18
 **/
@RestController
@RequestMapping(value = TransactionTypeResources.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionTypeResources {

  static final String BASE_URL = "/api/v1/transaction/type";

  @GetMapping
  public ResponseEntity<List<TransactionType>> getTransactionType() {
    return ResponseEntity.ok(Arrays.asList(TransactionType.values()));
  }
}
