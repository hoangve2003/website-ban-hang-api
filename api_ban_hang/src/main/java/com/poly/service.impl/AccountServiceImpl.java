package com.poly.service.impl;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;
import com.poly.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account findById(String username) {
        return accountDAO.findById(username).get();
    }

    @Override
    public List<Account> getAdministrator() {
        return accountDAO.getAdministrator();
    }

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }


}
