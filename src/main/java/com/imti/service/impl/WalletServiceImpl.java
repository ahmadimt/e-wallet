package com.imti.service.impl;

import com.imti.dto.TransactionDto;
import com.imti.dto.WalletDto;
import com.imti.exception.TransactionNotFoundException;
import com.imti.exception.WalletException;
import com.imti.exception.WalletNotFoundException;
import com.imti.persistence.helper.Status;
import com.imti.persistence.model.Transaction;
import com.imti.persistence.model.Wallet;
import com.imti.persistence.repo.TransactionRepo;
import com.imti.persistence.repo.WalletRepo;
import com.imti.service.WalletService;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by imteyaz on 03/12/18
 **/
@Service
public class WalletServiceImpl implements WalletService {

  private final WalletRepo walletRepo;

  private final TransactionRepo transactionRepo;

  @Value("${wallet.overdraft.limit:-50000}")
  private double overDraftLimit;

  @Autowired
  public WalletServiceImpl(WalletRepo walletRepo,
      TransactionRepo transactionRepo) {
    this.walletRepo = walletRepo;
    this.transactionRepo = transactionRepo;
  }

  @Override
  public WalletDto getWalletDetailsById(Long id) {
    return walletRepo.findById(id).map(WalletDto::from)
        .orElseThrow(() -> new WalletNotFoundException("No wallet found with id " + id));
  }

  @Override
  public WalletDto createWallet(WalletDto walletDto) {
    Wallet wallet = walletRepo.save(Wallet.from(walletDto));
    return Objects.nonNull(wallet) ? WalletDto.from(wallet) : new WalletDto();
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Transaction performTransaction(Long walletId, TransactionDto transactionDto) {
    Optional<Wallet> wallet = walletRepo.findById(walletId);
    return wallet.map(wallet1 -> getTransactions(wallet1, transactionDto))
        .orElseThrow(
            () -> new WalletNotFoundException("No details found for wallet id " + walletId));
  }

  @Override
  public List<TransactionDto> listTransactions(Long walletId) {
    return transactionRepo.findByWallet_Id(walletId).stream().filter(Objects::nonNull)
        .map(TransactionDto::fromTransaction)
        .sorted(Comparator.comparing(TransactionDto::getTimestamp).reversed()).collect(
            Collectors.toList());
  }

  @Override
  public TransactionDto deleteTransaction(Long walletId, UUID transactionId) {
    return transactionRepo
        .findByWallet_IdAndTransactionIdAndStatus(walletId, transactionId, Status.ACTIVE)
        .map(transaction -> doDelete(transaction, walletId))
        .orElseThrow(() -> new TransactionNotFoundException(
            "Details could not be found for transaction id: " + transactionId + " and wallet id:  "
                + walletId));
  }

  @Override
  public List<WalletDto> getAllWallets() {
    return walletRepo.findAll().stream().filter(Objects::nonNull).map(Wallet::to)
        .sorted(Comparator.comparing(WalletDto::getId)).collect(
            Collectors.toList());
  }

  private TransactionDto doDelete(Transaction transaction, Long walletId) {
    transaction.setStatus(Status.CANCELLED);
    Transaction transaction1 = transactionRepo.save(transaction);
    return walletRepo.findById(walletId).map(wallet -> updateBalance(wallet, transaction1))
        .orElse(new TransactionDto());
  }

  private TransactionDto updateBalance(Wallet wallet, Transaction transaction) {
    TransactionDto transactionDto;
    switch (transaction.getType()) {
      case CREDIT:
        wallet.setBalance(wallet.getBalance().subtract(transaction.getAmount()));
        break;
      case DEBIT:
        wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        break;
      default:
        throw new IllegalArgumentException("Not a valid transaction type" + transaction.getType());
    }
    Wallet save = walletRepo.save(wallet);
    if (Objects.nonNull(save)) {
      transactionDto = TransactionDto.from(transaction);
      transactionDto.setStatus(Status.CANCELLED);
    } else {
      throw new WalletException("Could not update the balance for wallet " + wallet.getId());
    }
    return transactionDto;
  }

  private Transaction getTransactions(Wallet wallet1, TransactionDto transactionDto) {
    switch (transactionDto.getType()) {
      case DEBIT:
        Transaction transaction1 = doDebit(transactionDto, wallet1);
        walletRepo.save(wallet1);
        return transaction1;
      case CREDIT:
        Transaction transaction = doCredit(transactionDto, wallet1);
        walletRepo.save(wallet1);
        return transaction;
      default:
        throw new IllegalArgumentException(
            transactionDto.getType() + " is not a valid transaction type ");
    }
  }

  private Transaction doCredit(TransactionDto transactionDto, Wallet wallet) {
    Transaction transaction1 = Transaction.from(transactionDto, wallet);
    Transaction transaction = transactionRepo
        .save(transaction1);
    wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
    return transaction;
  }

  private Transaction doDebit(TransactionDto transactionDto, Wallet wallet) {

    switch (wallet.getType()) {
      case REGULAR:
        if (wallet.getBalance().subtract(transactionDto.getAmount())
            .compareTo(BigDecimal.ZERO) == -1) {
          throw new WalletException(
              "The transaction is not allowed as there is no sufficient fund in wallet");
        }
        Transaction transaction = transactionRepo
            .save(Transaction.from(transactionDto, wallet));
        wallet.setBalance(wallet.getBalance().subtract(transaction.getAmount()));
        return transaction;
      case OVERDRAFT:
        if (wallet.getBalance().subtract(transactionDto.getAmount())
            .compareTo(BigDecimal.valueOf(overDraftLimit)) == -1) {
          throw new WalletException(
              "The transaction is not allowed as there is no sufficient fund in wallet");
        }
        Transaction transaction1 = transactionRepo
            .save(Transaction.from(transactionDto, wallet));
        wallet.setBalance(wallet.getBalance().subtract(transaction1.getAmount()));
        return transaction1;
      default:
        throw new IllegalArgumentException(wallet.getType() + " is not a valid type");
    }
  }
}

