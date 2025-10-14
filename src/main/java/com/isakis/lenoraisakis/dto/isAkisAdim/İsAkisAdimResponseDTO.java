package com.isakis.lenoraisakis.dto.isAkisAdim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class İsAkisAdimResponseDTO {
    private Integer adim_no;
    private String birim_tipi_oid;
    private Integer baslangıc;
    private String birim_oid;
    private Integer turu;
    private Integer adim_no_referans;
    private Integer adim_no_geri;

}
