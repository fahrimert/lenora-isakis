package com.isakis.lenoraisakis.service;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTanim.IsAkisTanimCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.IsAkisTanimUpdateRequestDTO;
import com.isakis.lenoraisakis.model.IsAkisTanim;
import org.springframework.http.ResponseEntity;

public interface İsAkisTanimService extends  BaseService<IsAkisTanim,String> {

    ResponseEntity<ApiResponse> deployIsAkis(String isAkisTanimOid);

    ResponseEntity<ApiResponse>  getAllİsAkis();

    ResponseEntity<ApiResponse> createİsAkis(IsAkisTanimCreateRequestDTO isAkisTanimCreateRequestDTO, String creatorPerson);

    //
    //    public ResponseEntity<ApiResponse> findByOid( String isAkisTanimId){
    //        Optional<İsAkisTanim> i̇sAkisTanim  = i̇sAkisTanimRepository.findById(oid);
    //
    //    }
    ResponseEntity   updateİsAkis(IsAkisTanimUpdateRequestDTO i̇sAkisTanimUpdateRequestDTO, String isAkisTanimId);
}
