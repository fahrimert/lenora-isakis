package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimCreateDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimInitializeRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IsAkisAdimService {
    public ResponseEntity<ApiResponse> initalizeIsAdım(IsAkisAdimInitializeRequestDTO isAkisAdimCreateRequestDTO, String kullaniciOid, String isAkisTanimOid);
    public ResponseEntity<ApiResponse> createIsAdim(IsAkisAdimCreateDTO isAkisAdimCreateRequestDTO, String kullaniciOid, String isAkisTanimOid);
    public ResponseEntity<ApiResponse> endIsAdim(IsAkisAdimInitializeRequestDTO isAkisAdimInitializeRequestDTO, String kullaniciOid, String isAkisTanimOid);

    public ResponseEntity<ApiResponse> updateIsAdim(IsAkisAdimUpdateRequestDTO isAkisAdimUpdateRequestDTO, String kullaniciOid, String isAkisAdimOid);
    public ResponseEntity<ApiResponse> getIsAdımsOfIsAkısTanım(String isAkisTanimOid);
    public ResponseEntity<ApiResponse> getIsAdımOfIsAkısTanım( String isAkisAdimiOid);
    public ResponseEntity<ApiResponse> getAllİsAdims();

    ResponseEntity<ApiResponse> deleteIsAkisAdim(String isAdimOid);

    ResponseEntity<ApiResponse> deleteAllByIsAkisTanimOid(String isAkisAdimOid);

    ResponseEntity<ApiResponse> updateKosul(String isAkisAdimOid,String kosul);

    ResponseEntity<ApiResponse> deleteKosul(String isAdimOid);
}
