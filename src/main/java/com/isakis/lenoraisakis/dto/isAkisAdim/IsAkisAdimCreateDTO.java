package com.isakis.lenoraisakis.dto.isAkisAdim;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisAdimCreateDTO {

    @NonNull
    private String adı;
    @NonNull
    private String aciklama;
    private String birim_tipi_oid;
    private String birim_oid;
    private Integer turu;
    private  Integer X;
    private  Integer Y;

}