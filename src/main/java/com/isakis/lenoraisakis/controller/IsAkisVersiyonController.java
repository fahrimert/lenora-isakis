package com.isakis.lenoraisakis.controller;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisVersion.İsAkisVersionCreateDTO;
import com.isakis.lenoraisakis.serviceimpl.IsAkisVersiyonServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isakisversiyon")
public class IsAkisVersiyonController {

    private final IsAkisVersiyonServiceImpl isAkisVersiyonServiceImpl;

    public IsAkisVersiyonController(IsAkisVersiyonServiceImpl isAkisVersiyonServiceImpl) {
        this.isAkisVersiyonServiceImpl = isAkisVersiyonServiceImpl;
    }


    @GetMapping("/getAll/{isAkisTanimId}")
    ResponseEntity<ApiResponse> getAll(@PathVariable String isAkisTanimId) {
        return isAkisVersiyonServiceImpl.getAllIsAkisVersiyonBasedOnIsAkisTanim( isAkisTanimId);
    }

    @PostMapping("/createİsAkisiVersion/{isAkisTanimId}")
    ResponseEntity<ApiResponse> createIsAkisiVersion(@RequestBody İsAkisVersionCreateDTO i̇sAkisVersionCreateDTO , @PathVariable String isAkisTanimId){
        return isAkisVersiyonServiceImpl.createIsAkisiVersion(i̇sAkisVersionCreateDTO, isAkisTanimId);
    }

    @GetMapping("/getSingleIsAkisTanimBasedOnVersion/{isAkisVersionId}")
    ResponseEntity<ApiResponse> getSingleIsAkisTanim( @PathVariable String isAkisVersionId){
        return isAkisVersiyonServiceImpl.getSingleIsAkisVersiyonBasedOnIsAkisTanim(isAkisVersionId);
    }

    @DeleteMapping("/deleteIsAkisVersion/{isAkisVersionId}/{isAkisTanimId}")
    ResponseEntity<ApiResponse> deleteIsAkisVersion( @PathVariable String isAkisVersionId,@PathVariable String isAkisTanimId){
        return isAkisVersiyonServiceImpl.deleteIsAkisiVersion(isAkisVersionId,isAkisTanimId);
    }

}


