package com.isakis.lenoraisakis.dto.isAkisAdim;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisAdimUpdateRequestDTO {
    private  String birimTipiOid;
    private  String birimOid;
    private String acıklama;
    //suan icin türünü updatelemeyi işin içine katmayacam

        private String isakis_grup_oid;

    private  Integer X;
    private  Integer Y;
    private Integer turu;
    private String kosul;
    private String ek;


}
