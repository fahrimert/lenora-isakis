package com.isakis.lenoraisakis.dto.isAkisTransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisTransferCreateRequestDTO {
//    private Integer isakis_adim_no; buna gerek olmayabilir sonuçta transferin adım numarasını napacazki
    private String sourceId;
    private String targetId;
    private  String komut;
    private  Integer turu;
    private String kosul;
    private String aciklama;
    private String hedef_ad_aciklama;
    private String kosul_aciklama;

}