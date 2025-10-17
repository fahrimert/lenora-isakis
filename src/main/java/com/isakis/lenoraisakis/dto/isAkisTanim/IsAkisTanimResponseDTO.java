package com.isakis.lenoraisakis.dto.isAkisTanim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class IsAkisTanimResponseDTO {
    private String adı;
    private String aciklama;
    private Integer aktif;
    private String creatorPerson;
    private LocalDateTime created;
    private Integer aktıfversıyon;
}
