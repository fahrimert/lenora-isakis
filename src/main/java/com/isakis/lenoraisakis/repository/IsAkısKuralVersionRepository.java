package com.isakis.lenoraisakis.repository;

import com.isakis.lenoraisakis.model.runtime.IsAkisKuralVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IsAkısKuralVersionRepository extends JpaRepository<IsAkisKuralVersion,String> {
}
