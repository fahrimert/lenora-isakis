package com.isakis.lenoraisakis.model.runtime;

import com.isakis.lenoraisakis.model.core.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IsAkisKuralVersion extends BaseModel {

    private String isKuralOid;

    @Column(name = "kural", length = 4000)
    private String kural;


    @Column(name = "aciklama", length = 512)
    private String aciklama;

        @Column(name = "versıon", length = 512)
    private String versıon;

        private  Integer aktıf;

    private String olusturan_oid;
    private String olusturan_zamanı;



}
