package com.isakis.lenoraisakis.dto.isAkisTanim;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class İsAkisTanimUpdateRequestDTO {
    private String adı;
    private String aciklama;
    private String aktif_isakis_versiyon_oid;

}