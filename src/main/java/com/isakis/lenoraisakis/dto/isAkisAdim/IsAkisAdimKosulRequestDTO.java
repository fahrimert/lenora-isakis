package com.isakis.lenoraisakis.dto.isAkisAdim;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisAdimKosulRequestDTO {

    private String kosul;
    private String eklenti;
    private String aciklama;
    private String kaynakAdimOid;
    private  String hedefAdimOid;


}

