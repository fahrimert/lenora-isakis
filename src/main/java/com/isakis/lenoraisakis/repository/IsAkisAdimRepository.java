package com.isakis.lenoraisakis.repository;


import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.İsAkisTanim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsAkisAdimRepository extends JpaRepository<IsAkisAdim,String> {
    IsAkisAdim findByAdimNo(Integer adimNo);
}
