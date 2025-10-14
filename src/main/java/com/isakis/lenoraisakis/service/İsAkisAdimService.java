package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisAdim.İsAkisAdimCreateDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.İsAkisAdimInitializeRequestDTO;
import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.İsAkisTanim;
import org.springframework.http.ResponseEntity;

public interface İsAkisAdimService {
    public ResponseEntity<ApiResponse> initalizeIsAdım(İsAkisAdimInitializeRequestDTO isAkisAdimCreateRequestDTO, String kullaniciOid, String isAkisTanimOid);
    public ResponseEntity<ApiResponse> createIsAdim(İsAkisAdimCreateDTO isAkisAdimCreateRequestDTO, String kullaniciOid, String isAkisTanimOid);
//    public ResponseEntity<ApiResponse> updateIsAdim(İsAkisAdimCreateDTO isAkisAdimCreateRequestDTO, String kullaniciOid, String isAkisTanimOid);
    public ResponseEntity<ApiResponse> getIsAdımsOfIsAkısTanım(String isAkisTanimOid);
    public ResponseEntity<ApiResponse> getIsAdımOfIsAkısTanım( String isAkisAdimiOid);


    ResponseEntity<ApiResponse> deleteIsAkisAdim(String isAdimOid);
}
