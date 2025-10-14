package com.isakis.lenoraisakis.service;

import org.springframework.http.ResponseEntity;

public interface BaseService <T,ID>{
    ResponseEntity findByOid(ID oid);
    void delete(ID oid);
}
