package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.model.runtime.IsAkisKural;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IsAkısKuralService extends JpaRepository<IsAkisKural,String> {
}
