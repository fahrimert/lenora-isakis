package com.isakis.lenoraisakis.dto.isAkisAdim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisAdimInitializeRequestDTO {
    private String adı;
    private String aciklama;
    private String birim_tipi_oid;
    private String birim_oid;
    private  Integer X;
    private  Integer Y;

}