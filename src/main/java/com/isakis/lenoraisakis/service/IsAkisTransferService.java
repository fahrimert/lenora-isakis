package com.isakis.lenoraisakis.service;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferCreateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IsAkisTransferService {

    public ResponseEntity<ApiResponse> createIsAkisTransfer(
            IsAkisTransferCreateRequestDTO isAkisTransferCreateRequestDTO,
            @PathVariable String isAkısTanımId);
}
