package com.isakis.lenoraisakis.serviceimpl;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.model.core.BaseModel;
import com.isakis.lenoraisakis.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

public class BaseServiceImpl<T extends BaseModel,ID> implements BaseService<T,ID> {

    protected JpaRepository<T,ID> repository;

    public BaseServiceImpl(JpaRepository<T,ID> repository) {
        this.repository = repository;
    }

    @Override
    public  ResponseEntity findByOid(ID oid) {
            T foundById = repository.findById(oid).orElseThrow(() -> new RuntimeException("Not found by oid"));

        if (!repository.existsById(oid)) {
            return ResponseEntity.status(409).body(ApiResponse.error("Not found by oid", Collections.emptyList(), HttpStatus.BAD_REQUEST));
        }

        return  ResponseEntity.ok(foundById);

    }

    public void delete(ID oid) {
        if (!repository.existsById(oid)) {
            throw new Error(new RuntimeException("Not found by oid"));
        }
        repository.deleteById(oid);

    }
}
