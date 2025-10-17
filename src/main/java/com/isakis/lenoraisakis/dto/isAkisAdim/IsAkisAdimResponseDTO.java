package com.isakis.lenoraisakis.dto.isAkisAdim;

import com.isakis.lenoraisakis.model.IsAkisTransfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisAdimResponseDTO {
    private String oid;
    private String birim_tipi_oid;
    private Integer baslangıc;
    private String birim_oid;
    private Integer turu;
    private List<String> gelenAkışOidList;
    private List<String> gidenAkışOidList;


}
