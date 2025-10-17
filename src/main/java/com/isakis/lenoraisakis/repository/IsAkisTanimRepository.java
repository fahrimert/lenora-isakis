package com.isakis.lenoraisakis.repository;

import com.isakis.lenoraisakis.model.IsAkisTanim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsAkisTanimRepository  extends JpaRepository<IsAkisTanim,String> {
}
