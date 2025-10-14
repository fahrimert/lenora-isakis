package com.isakis.lenoraisakis.dto.isAkisAdim;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class İsAkisAdimUpdateRequestDTO {
    private  String birimTipiOid;
    private  String birimOid;
    private String acıklama;


}
