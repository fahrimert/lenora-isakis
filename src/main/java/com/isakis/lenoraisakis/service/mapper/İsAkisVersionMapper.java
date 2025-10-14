package com.isakis.lenoraisakis.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimResponseDTO;
import com.isakis.lenoraisakis.dto.isAkisVersion.İsAkisVersionResponseDTO;
import com.isakis.lenoraisakis.model.IsAkisVersion;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service

public class İsAkisVersionMapper {

    private  final ObjectMapper objectMapper;

    public İsAkisVersionMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public İsAkisVersionResponseDTO fromİsAkisVersion(@Valid IsAkisVersion isAkisVersion){
        İsAkisVersionResponseDTO i̇sAkisVersionResponseDTO = new İsAkisVersionResponseDTO();
        i̇sAkisVersionResponseDTO.setAciklama(i̇sAkisVersionResponseDTO.getAciklama());
        i̇sAkisVersionResponseDTO.setAktıfversıyon( isAkisVersion.getVersion());
        i̇sAkisVersionResponseDTO.setAktif(isAkisVersion.getAktif());
        i̇sAkisVersionResponseDTO.setCreatorPerson(isAkisVersion.getCreatorPerson());
        i̇sAkisVersionResponseDTO.setIsakis_tanim_adi(isAkisVersion.getIsakis_tanim_adi());
        return  i̇sAkisVersionResponseDTO;
    }

}
