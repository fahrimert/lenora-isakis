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
public class IsAkisGenelLog extends BaseModel {
    private  String kullanıcı_oid;
    private String vekil_kullanıcı_oid;
    private  String gorev_oid;
    private  String zaman;
    private  Integer object_oıd;
    private  Integer ıslem;
    private  String aciklama;
    private  String ıp;
    private  Integer version;
}
