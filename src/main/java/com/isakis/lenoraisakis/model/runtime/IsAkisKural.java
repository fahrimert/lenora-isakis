package com.isakis.lenoraisakis.model.runtime;

import com.isakis.lenoraisakis.model.core.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IsAkisKural extends BaseModel {
    private String adi;
    private  String aciklama;
    private  Integer durum;
    private  Integer aktifVersion;
    private  String olusturan_oid;
    private  String olusturma_zamani;
    private  String sahip_oid;
}
