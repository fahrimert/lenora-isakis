package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.model.runtime.IsAkisEklentiVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IsAkısEklentiVersioniService extends JpaRepository<IsAkisEklentiVersion,String> {
}
