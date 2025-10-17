package com.isakis.lenoraisakis.repository;

import com.isakis.lenoraisakis.model.IsAkisTanim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsAkisDurumRepository extends JpaRepository<IsAkisTanim,String> {
}
