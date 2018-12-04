package com.imti.service;

import com.imti.dto.TransactionDto;
import com.imti.dto.WalletDto;
import com.imti.persistence.model.Transaction;
import java.util.List;
import java.util.UUID;

/**
 * Created by imteyaz on 03/12/18
 **/

public interface WalletService {

  WalletDto getWalletDetailsById(Long id);

  WalletDto createWallet(WalletDto walletDto);

  Transaction performTransaction(Long walletId, TransactionDto transactionDto);

  List<TransactionDto> listTransactions(Long walletId);

  TransactionDto deleteTransaction(Long walletId, UUID transactionId);

  List<WalletDto> getAllWallets();

}
