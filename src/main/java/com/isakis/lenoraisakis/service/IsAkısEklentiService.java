package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.model.runtime.IsAkisEklenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IsAkısEklentiService extends JpaRepository<IsAkisEklenti,String> {
}
