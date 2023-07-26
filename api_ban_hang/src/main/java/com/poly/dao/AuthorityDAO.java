package com.poly.dao;

import com.poly.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {
}
