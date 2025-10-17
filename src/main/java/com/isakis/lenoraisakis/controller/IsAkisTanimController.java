package com.isakis.lenoraisakis.controller;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTanim.IsAkisTanimCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.IsAkisTanimUpdateRequestDTO;
import com.isakis.lenoraisakis.service.İsAkisTanimService;
import com.isakis.lenoraisakis.serviceimpl.İsAkisTanimServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isakistanim")
public class IsAkisTanimController {
    private  final İsAkisTanimServiceImpl ısAkisTanimServiceImpl;


    public IsAkisTanimController(İsAkisTanimServiceImpl ısAkisTanimServiceImpl) {
        this.ısAkisTanimServiceImpl = ısAkisTanimServiceImpl;
    }

    @GetMapping("/getAll")
    ResponseEntity<ApiResponse> getAll(){
        return ısAkisTanimServiceImpl.getAllİsAkis();
    }

    @PostMapping("/create/{createdBy}")
    ResponseEntity<ApiResponse> createIsAkıs(@RequestBody IsAkisTanimCreateRequestDTO isAkisTanimCreateRequestDTO, @PathVariable  String createdBy){
        return ısAkisTanimServiceImpl.createİsAkis(isAkisTanimCreateRequestDTO,createdBy);
    }

    @PutMapping("/update/{isAkisTanimId}")
    ResponseEntity<ApiResponse> updateIsAkıs(@RequestBody IsAkisTanimUpdateRequestDTO i̇sAkisTanimUpdateRequestDTO, @PathVariable  String isAkisTanimId){
        return ısAkisTanimServiceImpl.updateİsAkis(i̇sAkisTanimUpdateRequestDTO,isAkisTanimId);
    }

    @DeleteMapping("/{isAkisTanimId}")
    void deleteIsAkıs(@PathVariable  String isAkisTanimId){
        ısAkisTanimServiceImpl.delete(isAkisTanimId);
    }


    @PostMapping("/deployIsAkis/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> deployIsAkis(
                                             @PathVariable  String isAkisTanimOid){
        return ısAkisTanimServiceImpl.deployIsAkis(isAkisTanimOid);
    }

    @PostMapping("/runIsAkis/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> runIsAkis(  @PathVariable  String isAkisTanimOid){
        return ısAkisTanimServiceImpl.runIsAkis(isAkisTanimOid);
    }


    @PostMapping("/create/{createdBy}")
    ResponseEntity<ApiResponse> runIsAkis(@RequestBody IsAkisTanimCreateRequestDTO isAkisTanimCreateRequestDTO, @PathVariable  String createdBy){
        return ısAkisTanimServiceImpl.createİsAkis(isAkisTanimCreateRequestDTO,createdBy);
    }
}
