package com.isakis.lenoraisakis.dto.isAkisVersion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisVersionCreateDTO {
    private Integer versiyon;
    private String aciklama;
    private String olusturan_oid;

}