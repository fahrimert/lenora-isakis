package com.isakis.lenoraisakis.service;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IsAkisTransferService {

    public ResponseEntity<ApiResponse> createIsAkisTransfer(
            IsAkisTransferCreateRequestDTO isAkisTransferCreateRequestDTO,
            @PathVariable String isAkısTanımId);

    public ResponseEntity<ApiResponse> deleteAllIsAkisTransfers(String isAkisTransferOid);

    public  ResponseEntity<ApiResponse> deleteSingleIsAkisTransfer( String isAkisTransferOid);

    public  ResponseEntity<ApiResponse> updateSingleAkisTransfer(
            IsAkisTransferUpdateDTO isAkisTransferUpdateDTO,
            String isAkisTransferOid);

    ResponseEntity<ApiResponse> updateKosul(String isTransferOid, String kosul);

    ResponseEntity<ApiResponse> deleteKosul(String isTransferOid);
}
