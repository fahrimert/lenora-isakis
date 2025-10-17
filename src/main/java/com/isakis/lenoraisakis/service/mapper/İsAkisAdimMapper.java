package com.isakis.lenoraisakis.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimResponseDTO;
import com.isakis.lenoraisakis.model.IsAkisAdim;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class İsAkisAdimMapper {
    private  final ObjectMapper objectMapper;


    public İsAkisAdimMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public IsAkisAdimResponseDTO fromİsAdim(@Valid IsAkisAdim isAkisAdim){
        IsAkisAdimResponseDTO i̇sAkisAdimResponseDTO = new IsAkisAdimResponseDTO();
        i̇sAkisAdimResponseDTO.setBirim_tipi_oid(isAkisAdim.getBirim_tipi_oid());
        i̇sAkisAdimResponseDTO.setBirim_oid(isAkisAdim.getBirim_oid());
        i̇sAkisAdimResponseDTO.setBaslangıc(isAkisAdim.getBaslangıc());
        i̇sAkisAdimResponseDTO.setBaslangıc(isAkisAdim.getEnd());
        i̇sAkisAdimResponseDTO.setTuru(isAkisAdim.getTuru());
        i̇sAkisAdimResponseDTO.setOid(isAkisAdim.getOid());
        i̇sAkisAdimResponseDTO.setGelenAkışOidList( isAkisAdim.getGelenAkış().stream().map(a -> a.getOid()).toList());
        i̇sAkisAdimResponseDTO.setGidenAkışOidList( isAkisAdim.getGidenAkış().stream().map(a -> a.getOid()).toList());
        return  i̇sAkisAdimResponseDTO;
    }
}
