package com.poly.dao;


import com.poly.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account, String> {

    @Query("SELECT DISTINCT ar.account FROM Authority ar where ar.role.id IN ('DIRE','STAF')")
    List<Account> getAdministrator();
}
