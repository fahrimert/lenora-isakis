package com.isakis.lenoraisakis.dto.isAkisVersion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class İsAkisVersionResponseDTO {
    private String isakis_tanim_adi;
    private  Integer versiyon;
    private String aciklama;
    private String olusturma_zamanı;

    private String creatorPerson;
    private Integer aktif;
    private Integer aktıfversıyon;
}
