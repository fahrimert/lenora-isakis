package com.isakis.lenoraisakis.controller;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimUpdateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferCreateRequestDTO;
import com.isakis.lenoraisakis.service.IsAkisTransferService;
import com.isakis.lenoraisakis.serviceimpl.İsAkisTanimServiceImpl;
import com.isakis.lenoraisakis.serviceimpl.İsAkisTransferServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isakistransfer")
public class İsAkisTransferController {
    private  final IsAkisTransferService i̇sAkisTransferService;
    private final IsAkisTransferService isAkisTransferService;

    public İsAkisTransferController(IsAkisTransferService i̇sAkisTransferService, IsAkisTransferService isAkisTransferService) {
        this.i̇sAkisTransferService = i̇sAkisTransferService;
        this.isAkisTransferService = isAkisTransferService;
    }
//    @PostMapping("/getAll/{isAkısTanımId}")
//    ResponseEntity<ApiResponse> getAllAkisTransfer(@PathVariable  String isAkısTanımId
//    ){
//        return isAkisTransferService.getAllAkisTransfer(isAkisTransferCreateRequestDTO,isAkısTanımId);
//    }
    @PostMapping("/create/{isAkısTanımId}")
    ResponseEntity<ApiResponse> createIsAkısTransfer(@RequestBody IsAkisTransferCreateRequestDTO isAkisTransferCreateRequestDTO,
                                                     @PathVariable  String isAkısTanımId
                                                     ){
        return isAkisTransferService.createIsAkisTransfer(isAkisTransferCreateRequestDTO,isAkısTanımId);
    }
}
