package com.poly.service;

import com.poly.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findAuthoritiesOfAdministrator();

    List<Authority> findAll();

    Authority create(Authority auth);

    void delete(Integer id);
}
