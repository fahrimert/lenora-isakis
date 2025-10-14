package com.isakis.lenoraisakis.repository;

import com.isakis.lenoraisakis.model.İsAkisTanim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface İsAkisTanimRepository  extends JpaRepository<İsAkisTanim,String> {
}
