package com.imti.persistence.repo;

import com.imti.persistence.helper.Status;
import com.imti.persistence.model.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by imteyaz on 03/12/18
 **/

public interface TransactionRepo extends JpaRepository<Transaction, String> {

  List<Transaction> findByWallet_Id(Long walletId);

  Optional<Transaction> findByWallet_IdAndTransactionIdAndStatus(Long walletId, UUID transactionId,
      Status status);
}
