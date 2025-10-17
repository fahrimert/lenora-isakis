package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.model.runtime.IsAkisEklenti;
import com.isakis.lenoraisakis.model.runtime.IsAkısDurum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IsAkısDurumService extends JpaRepository<IsAkısDurum,String> {
}
