package com.imti.persistence.repo;

import com.imti.persistence.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by imteyaz on 01/12/18
 **/

public interface WalletRepo extends JpaRepository<Wallet, Long> {

}
