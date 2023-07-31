package com.poly.service.impl;

import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDAO authorityDAO;
    @Autowired
    AccountDAO accountDAO;

    @Override
    public List<Authority> findAuthoritiesOfAdministrator() {
        List<Account> accounts = accountDAO.getAdministrator();
        return authorityDAO.authoritiesOf(accounts);
    }

    @Override
    public List<Authority> findAll() {
        return authorityDAO.findAll();
    }

    @Override
    public Authority create(Authority auth) {
        return authorityDAO.save(auth);
    }

    @Override
    public void delete(Integer id) {
        authorityDAO.deleteById(id);
    }
}
