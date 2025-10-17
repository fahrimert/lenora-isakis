package com.isakis.lenoraisakis.repository;

import com.isakis.lenoraisakis.model.IsAkisTanim;
import com.isakis.lenoraisakis.model.IsAkisVersion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface İsAkisVersiyonRepository  extends JpaRepository<IsAkisVersion,String>  {


    @Modifying
    @Transactional
    @Query(value = """
              SELECT * FROM is_akis_version
                                     WHERE isAkisVersion.is_akis_version_id = :isAkisTanimOid 
                                                            AND aktif=1 """
            , nativeQuery = true)
    Optional<IsAkisVersion> findByIsAkisTanimOidAndAktıf(String isAkisTanimOid);


}
