package com.isakis.lenoraisakis.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.IsAkisTransfer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class İsAkisTransferMapper {
    private  final ObjectMapper objectMapper;


    public İsAkisTransferMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
//
//    public İsAkisTransferResponseDTO fromİsAkisTransfer(@Valid IsAkisTransfer isAkisTransfer){
//        İsAkisAdimResponseDTO i̇sAkisAdimResponseDTO = new İsAkisAdimResponseDTO();
//        i̇sAkisAdimResponseDTO.setBirim_tipi_oid(isAkisAdim.getBirim_tipi_oid());
//        i̇sAkisAdimResponseDTO.setBirim_oid(isAkisAdim.getBirim_oid());
//        i̇sAkisAdimResponseDTO.setBaslangıc(isAkisAdim.getBaslangıc());
//        i̇sAkisAdimResponseDTO.setTuru(isAkisAdim.getTuru());
//        i̇sAkisAdimResponseDTO.setAdim_no(isAkisAdim.getAdim_no());
//        i̇sAkisAdimResponseDTO.setAdim_no_geri(isAkisAdim.getAdim_no_geri());
//        i̇sAkisAdimResponseDTO.setAdim_no_referans(isAkisAdim.getAdim_no_referans());
//
//        return  i̇sAkisAdimResponseDTO;
//    }
}
