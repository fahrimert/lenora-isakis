package com.isakis.lenoraisakis.repository;


import com.isakis.lenoraisakis.model.IsAkisAdim;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IsAkisAdimRepository extends JpaRepository<IsAkisAdim,String> {

    @Query(value = """
    SELECT * 
    FROM is_akis_adim 
    WHERE adim_no = :adimNo 
    """, nativeQuery = true)
    IsAkisAdim findByAdimNo(Integer adimNo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM is_akis_adim WHERE is_akis_adim.is_akis_tanim_id = :isAkisTanimOid", nativeQuery = true)

    void deleteAllByIsAkisTanimOid(@Param("isAkisTanimOid") String isAkisTanimOid);
}
