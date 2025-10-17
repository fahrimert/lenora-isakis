package com.isakis.lenoraisakis.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakis.lenoraisakis.dto.isAkisTanim.IsAkisTanimResponseDTO;
import com.isakis.lenoraisakis.model.IsAkisTanim;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class İsAkisTanimMapper {

    private  final ObjectMapper objectMapper;


    public İsAkisTanimMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public IsAkisTanimResponseDTO fromİsAkisTanim(@Valid IsAkisTanim isAkisTanim){
        IsAkisTanimResponseDTO i̇sAkisTanimResponseDTO = new IsAkisTanimResponseDTO();
        i̇sAkisTanimResponseDTO.setAdı(isAkisTanim.getAdı());
        i̇sAkisTanimResponseDTO.setAciklama(isAkisTanim.getAciklama());
        i̇sAkisTanimResponseDTO.setCreatorPerson(isAkisTanim.getCreatorPerson());
        i̇sAkisTanimResponseDTO.setAktif(isAkisTanim.getAktif());
        i̇sAkisTanimResponseDTO.setAktıfversıyon(isAkisTanim.getAktıfversıyon());
        isAkisTanim.setCreated(isAkisTanim.getCreated());

        isAkisTanim.setAktıfversıyon(isAkisTanim.getAktıfversıyon());
return  i̇sAkisTanimResponseDTO;
    }
}
