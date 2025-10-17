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
public class IsAkisEklenti extends BaseModel {
    private  String aciklama;
    private  String adi;
    private  Integer aktıfVersion;
    private  Integer durum;
    private  String isAkisEklentiVersionOid;
    private  String isAkisEklentiOlusturanOid;
    private  String isAkisEklentiOlusturmaZamanı;
    private  String sahipOid;




}
