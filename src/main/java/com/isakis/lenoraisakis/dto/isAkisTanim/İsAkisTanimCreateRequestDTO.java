package com.isakis.lenoraisakis.dto.isAkisTanim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class İsAkisTanimCreateRequestDTO {
    private String adı;
    private String aciklama;
}