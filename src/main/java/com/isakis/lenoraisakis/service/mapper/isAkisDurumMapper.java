package com.isakis.lenoraisakis.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakis.lenoraisakis.dto.isAkisVersion.IsAkisVersionResponseDTO;
import com.isakis.lenoraisakis.model.IsAkisVersion;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service

public class isAkisDurumMapper {

    private  final ObjectMapper objectMapper;

    public isAkisDurumMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public IsAkisVersionResponseDTO fromİsAkisVersion(@Valid IsAkisVersion isAkisVersion){
        IsAkisVersionResponseDTO i̇sAkisVersionResponseDTO = new IsAkisVersionResponseDTO();
        i̇sAkisVersionResponseDTO.setAciklama(i̇sAkisVersionResponseDTO.getAciklama());
        i̇sAkisVersionResponseDTO.setAktıfversıyon( isAkisVersion.getVersion());
        i̇sAkisVersionResponseDTO.setAktif(isAkisVersion.getAktif());
        i̇sAkisVersionResponseDTO.setCreatorPerson(isAkisVersion.getCreatorPerson());
        i̇sAkisVersionResponseDTO.setIsakis_tanim_adi(isAkisVersion.getIsakis_tanim_adi());
        return  i̇sAkisVersionResponseDTO;
    }

}
