package com.isakis.lenoraisakis.controller;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisAdim.İsAkisAdimCreateDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.İsAkisAdimInitializeRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimCreateRequestDTO;
import com.isakis.lenoraisakis.service.İsAkisAdimService;
import com.isakis.lenoraisakis.serviceimpl.IsAkisVersiyonServiceImpl;
import com.isakis.lenoraisakis.serviceimpl.İsAkisAdimServiceImpl;
import com.isakis.lenoraisakis.serviceimpl.İsAkisTanimServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/isakisadim")
public class İsAkisAdimController {

    private  final İsAkisAdimService isAkisAdimService;

    public İsAkisAdimController(İsAkisAdimServiceImpl isAkisAdimServiceImpl, İsAkisAdimService isAkisAdimService) {
        this.isAkisAdimService = isAkisAdimService;
    }
    @PostMapping("/initialize/{kullaniciOid}/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> initalizeIsAdım(@RequestBody İsAkisAdimInitializeRequestDTO i̇sAkisAdimCreateRequestDTO,
                                             @PathVariable String kullaniciOid,
                                             @PathVariable String isAkisTanimOid){
        return isAkisAdimService.initalizeIsAdım(
                i̇sAkisAdimCreateRequestDTO,
                kullaniciOid,
                isAkisTanimOid);
    }

    @PostMapping("/create/{kullaniciOid}/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> createIsAdım(@RequestBody İsAkisAdimCreateDTO i̇sAkisAdimCreateDTO,
                                                @PathVariable String kullaniciOid,
                                                @PathVariable String isAkisTanimOid){
        return isAkisAdimService.createIsAdim(i̇sAkisAdimCreateDTO,kullaniciOid,isAkisTanimOid);
    }

    //işin içine  iş transferi de igirnce bu updateleme ve deleteleme şimdilik kalsın
//    @PutMapping("/{kullaniciOid}/{isAkisAdimOid}")
//    ResponseEntity<ApiResponse> updateIsAdım(@RequestBody İsAkisAdimCreateDTO i̇sAkisAdimCreateDTO,
//                                             @PathVariable String kullaniciOid,
//                                             @PathVariable String isAkisTanimOid){
//        return isAkisAdimService.createIsAdim(i̇sAkisAdimCreateDTO,kullaniciOid,isAkisTanimOid);
//    }
    //deleteleme transferlerle beraber en son
//    @PostMapping("/{kullaniciOid}/{isAkisTanimOid}")
//    ResponseEntity<ApiResponse> deleteIsAdim(@RequestBody İsAkisAdimCreateDTO i̇sAkisAdimCreateDTO,
//                                             @PathVariable String kullaniciOid,
//                                             @PathVariable String isAkisTanimOid){
//        return isAkisAdimService.createIsAdim(i̇sAkisAdimCreateDTO,kullaniciOid,isAkisTanimOid);
//    }
    @GetMapping("/getIsAdıms/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> getIsAdımsOfIsAkısTanım(@PathVariable  String isAkisTanimOid){
        return isAkisAdimService.getIsAdımsOfIsAkısTanım(isAkisTanimOid);
    }
    @GetMapping("/getIsAdım/{isAdimOid}")
    ResponseEntity<ApiResponse> getIsAdımOfIsAkısTanım(@PathVariable String isAdimOid){
        return isAkisAdimService.getIsAdımOfIsAkısTanım(isAdimOid);
    }

    @DeleteMapping("/deleteIsAdım/{isAdimOid}")
    ResponseEntity<ApiResponse> deleteIsAkısAdım(@PathVariable String isAdimOid){
        return isAkisAdimService.deleteIsAkisAdim(isAdimOid);
    }

    @GetMapping("/getAll")
    ResponseEntity<ApiResponse> getAll(){
        return isAkisAdimServiceImpl.getAllİsAdims();
    }



}
