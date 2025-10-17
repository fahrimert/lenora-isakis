package com.isakis.lenoraisakis.repository;

import com.isakis.lenoraisakis.model.IsAkisAdim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IsAkısKuralRepository extends JpaRepository<IsAkisAdim,String> {
}
