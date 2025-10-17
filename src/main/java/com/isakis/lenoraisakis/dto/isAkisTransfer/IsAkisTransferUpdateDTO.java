package com.isakis.lenoraisakis.dto.isAkisTransfer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisTransferUpdateDTO {

    private Integer isakis_adim_no;
    private Integer sonraki_isakis_adim_no;
    private String komut;
    private  Integer turu;
    private String kosul;
    private String eklenti;
    private String aciklama;
    private String kaynakAdimOid;
    private  String hedefAdimOid;


}

