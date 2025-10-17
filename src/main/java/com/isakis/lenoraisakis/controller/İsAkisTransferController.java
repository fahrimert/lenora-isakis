package com.isakis.lenoraisakis.controller;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferUpdateDTO;
import com.isakis.lenoraisakis.service.IsAkisTransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isakistransfer")
public class İsAkisTransferController {
    private final IsAkisTransferService isAkisTransferService;

    public İsAkisTransferController( IsAkisTransferService isAkisTransferService) {
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


    @DeleteMapping("/deleteAll/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> deleteAllAkisTransfer(@PathVariable  String isAkisTanimOid
    ){
        return isAkisTransferService.deleteAllIsAkisTransfers(isAkisTanimOid);
    }



    @PutMapping("/update/{isAkisTransferOid}")
    ResponseEntity<ApiResponse> updateSingleTransfer(
            @RequestBody IsAkisTransferUpdateDTO isAkisTransferUpdateDTO,
            @PathVariable  String isAkisTransferOid
    ){
        return isAkisTransferService.updateSingleAkisTransfer(isAkisTransferUpdateDTO,isAkisTransferOid);
    }


    @DeleteMapping("/delete/{isAkisTransferOid}")
    ResponseEntity<ApiResponse> deleteSingleIsAkisTransfer(
                                                           @PathVariable  String isAkisTransferOid
    ){
        return isAkisTransferService.deleteSingleIsAkisTransfer(isAkisTransferOid);
    }


    @PutMapping("/{isAkisTransferOid}/updateKosul")
    ResponseEntity<ApiResponse> updateTransferKosul(@PathVariable String isAkisTransferOid , @RequestParam String kosul){
        return isAkisTransferService.updateKosul(isAkisTransferOid, kosul);
    }


    @DeleteMapping("/{isAkisTransferOid}/deleteKosul")
    ResponseEntity<ApiResponse> deleteTransferKosul(@PathVariable String isAkisTransferOid){
        return isAkisTransferService.deleteKosul(isAkisTransferOid);
    }

}
