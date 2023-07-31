package com.poly.service;

import com.poly.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAdministrator();

    List<Account> findAll();

    Account findById(String username);
}
