package com.isakis.lenoraisakis.repository;

import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.IsAkisTransfer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface İsAkisTransferRepository extends JpaRepository<IsAkisTransfer,String> {
    @Query  (value = "SELECT * FROM is_akis_transfer ORDER BY sıra DESC LIMIT 1", nativeQuery = true)
    Optional<IsAkisTransfer> getMaxSiraİsAkisTransfer();

    @Query  (value =
            """
SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END
        FROM is_akis_transfer
        WHERE kaynak_adim_oid =:oid
          AND hedef_adim_oid=:oid1 """
            , nativeQuery = true)
    boolean existBySourceAndTargetId(String oid, String oid1);

    @Query(value = """
    SELECT * 
    FROM is_akis_transfer 
    WHERE kaynak_adim_oid = :adimNoGeri 
      AND sonraki_isakis_adim_no = :adimNoReferans
    """, nativeQuery = true)
    IsAkisTransfer findByKaynakOidAndReferansNo(@Param("adimNoGeri")  Integer adimNoGeri,

                                                @Param("adimNoReferans")  Integer adimNoReferans);

    IsAkisTransfer findBySıra(int i);


    @Modifying
    @Transactional
    @Query(value = """
    DELETE FROM is_akis_transfer
    WHERE kaynak_adim_oid = :adimOid
       OR hedef_adim_oid = :adimOid
    """, nativeQuery = true)
    void deleteAllByKaynakAdimOidOrHedefAdimOid(@Param("adimOid") String adimOid);}

