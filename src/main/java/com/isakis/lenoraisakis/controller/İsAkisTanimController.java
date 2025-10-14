package com.isakis.lenoraisakis.controller;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimUpdateRequestDTO;
import com.isakis.lenoraisakis.serviceimpl.İsAkisTanimServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isakistanim")
public class İsAkisTanimController {
    private  final İsAkisTanimServiceImpl i̇sAkisTanimServiceImpl;


    public İsAkisTanimController(İsAkisTanimServiceImpl i̇sAkisTanimServiceImpl) {
        this.i̇sAkisTanimServiceImpl = i̇sAkisTanimServiceImpl;
    }

    @GetMapping("/getAll")
    ResponseEntity<ApiResponse> getAll(){
        return i̇sAkisTanimServiceImpl.getAllİsAkis();
    }

    @PostMapping("/create/{createdBy}")
    ResponseEntity<ApiResponse> createIsAkıs(@RequestBody İsAkisTanimCreateRequestDTO isAkisTanimCreateRequestDTO,@PathVariable  String createdBy){
        return i̇sAkisTanimServiceImpl.createİsAkis(isAkisTanimCreateRequestDTO,createdBy);
    }

    @PutMapping("/update/{isAkisTanimId}")
    ResponseEntity<ApiResponse> updateIsAkıs(@RequestBody İsAkisTanimUpdateRequestDTO i̇sAkisTanimUpdateRequestDTO, @PathVariable  String isAkisTanimId){
        return i̇sAkisTanimServiceImpl.updateİsAkis(i̇sAkisTanimUpdateRequestDTO,isAkisTanimId);
    }

    @DeleteMapping("/{isAkisTanimId}")
    void deleteIsAkıs(@PathVariable  String isAkisTanimId){
         i̇sAkisTanimServiceImpl.delete(isAkisTanimId);
    }

}
