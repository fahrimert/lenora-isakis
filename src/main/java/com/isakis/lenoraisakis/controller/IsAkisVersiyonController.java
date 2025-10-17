package com.isakis.lenoraisakis.controller;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisVersion.IsAkisVersionCreateDTO;
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
    ResponseEntity<ApiResponse> createIsAkisiVersion(@RequestBody IsAkisVersionCreateDTO i̇sAkisVersionCreateDTO , @PathVariable String isAkisTanimId){
        return isAkisVersiyonServiceImpl.createIsAkisiVersion(i̇sAkisVersionCreateDTO, isAkisTanimId);
    }

    @GetMapping("/getSingleIsAkisTanimBasedOnVersion/{isAkisVersionId}")
    ResponseEntity<ApiResponse> getSingleIsAkisTanim( @PathVariable String isAkisVersionId){
        return isAkisVersiyonServiceImpl.getSingleIsAkisVersiyonBasedOnIsAkisTanim(isAkisVersionId);
    }

    @PutMapping("/updateIsAkisVersiyon/{isAkisTanimId}")
    ResponseEntity<ApiResponse> updateIsAkisVersiyon( @RequestParam String isAkisVersionIdUpdated,@PathVariable String isAkisTanimId){
        return isAkisVersiyonServiceImpl.updateIsAkisVersiyon(isAkisVersionIdUpdated,isAkisTanimId);
    }


    @DeleteMapping("/deleteIsAkisVersion/{isAkisVersionId}/{isAkisTanimId}")
    ResponseEntity<ApiResponse> deleteIsAkisVersion( @PathVariable String isAkisVersionId,@PathVariable String isAkisTanimId){
        return isAkisVersiyonServiceImpl.deleteIsAkisiVersion(isAkisVersionId,isAkisTanimId);
    }

}


