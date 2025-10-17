package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.model.runtime.IsAkisKuralVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IsAkısKuralVersionService extends JpaRepository<IsAkisKuralVersion,String> {
}
