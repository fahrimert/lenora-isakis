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
public class IsAkisIslemDegisken extends BaseModel {
    private  String ıslem_oid;
    private  String sevk_oid;
    private  Integer aktıf;
    private  String olusturan_oid;
    private  String ıslem_zamani ;
    private  Integer tıp;
    private  String anahtar ;
    private  String deger ;
    private  Integer version;
}
