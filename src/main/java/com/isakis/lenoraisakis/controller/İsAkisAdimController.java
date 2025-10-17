package com.isakis.lenoraisakis.controller;


import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimCreateDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimInitializeRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimUpdateRequestDTO;
import com.isakis.lenoraisakis.service.IsAkisAdimService;
import com.isakis.lenoraisakis.serviceimpl.IsAkisAdimServiceImpl;
import com.isakis.lenoraisakis.serviceimpl.IsAkisVersiyonServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/isakisadim")
public class İsAkisAdimController {

    private  final IsAkisAdimService isAkisAdimService;

    public İsAkisAdimController(IsAkisAdimServiceImpl isAkisAdimServiceImpl, IsAkisAdimService isAkisAdimService) {
        this.isAkisAdimService = isAkisAdimService;
    }
    @PostMapping("/initialize/{kullaniciOid}/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> initalizeIsAdım(@RequestBody IsAkisAdimInitializeRequestDTO i̇sAkisAdimCreateRequestDTO,
                                             @PathVariable String kullaniciOid,
                                             @PathVariable String isAkisTanimOid){
        return isAkisAdimService.initalizeIsAdım(
                i̇sAkisAdimCreateRequestDTO,
                kullaniciOid,
                isAkisTanimOid);
    }

    @PostMapping("/create/{kullaniciOid}/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> createIsAdım(@RequestBody IsAkisAdimCreateDTO i̇sAkisAdimCreateDTO,
                                                @PathVariable String kullaniciOid,
                                                @PathVariable String isAkisTanimOid){
        return isAkisAdimService.createIsAdim(i̇sAkisAdimCreateDTO,kullaniciOid,isAkisTanimOid);
    }

    @PutMapping("/update/{kullaniciOid}/{isAkisAdimOid}")
    ResponseEntity<ApiResponse> updateIsAdım(@RequestBody IsAkisAdimUpdateRequestDTO isAkisAdimUpdateRequestDTO,
                                             @PathVariable String kullaniciOid,
                                             @PathVariable String isAkisAdimOid){
        return isAkisAdimService.updateIsAdim(isAkisAdimUpdateRequestDTO,kullaniciOid,isAkisAdimOid);
    }

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

    //bu direkt tüm iş tanımlarındaki tüm iş adımlarını temsil ediyor.
    @GetMapping("/getAllIsAdims")
    ResponseEntity<ApiResponse> getAllIsAdims(){
        return isAkisAdimService.getAllİsAdims();
    }

    @PutMapping("/{isAdimOid}/updateKosul")
    ResponseEntity<ApiResponse> updateKosul(@PathVariable String isAdimOid , @RequestParam String kosul){
        return isAkisAdimService.updateKosul(isAdimOid, kosul);
    }



    @DeleteMapping("/{isAdimOid}/deleteKosul")
    ResponseEntity<ApiResponse> deleteKosul(@PathVariable String isAdimOid ){
        return isAkisAdimService.deleteKosul(isAdimOid);
    }





    @DeleteMapping("/deleteAllIsAkisAdims/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> deleteAllIsAkisAdims(@PathVariable  String isAkisTanimOid){
        return isAkisAdimService.deleteAllByIsAkisTanimOid(isAkisTanimOid);
    }

    @PostMapping("/endIsAdim/{kullaniciOid}/{isAkisTanimOid}")
    ResponseEntity<ApiResponse> endIsAdim(@RequestBody IsAkisAdimInitializeRequestDTO i̇sAkisAdimCreateRequestDTO,
                                                @PathVariable String kullaniciOid,
                                                @PathVariable String isAkisTanimOid){
        return isAkisAdimService.endIsAdim(
                i̇sAkisAdimCreateRequestDTO,
                kullaniciOid,
                isAkisTanimOid);
    }
}
