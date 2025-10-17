package com.isakis.lenoraisakis.model.runtime;

import com.isakis.lenoraisakis.model.core.BaseModel;
import jakarta.persistence.Column;
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
public class IsAkisEklentiVersion extends BaseModel {

    private String isAkısEklentiOid;

    @Column(name = "aktıf", length = 4000)
    private Integer aktıf;


    @Column(name = "eklenti", length = 512)
    private String eklenti;

    @Column(name = "eklenti_oid", length = 512)
    private String eklenti_oid;

    private String aciklama;

    private  Integer version;

    private String olusturan_oid;
    private String olusturan_zamanı;



}
