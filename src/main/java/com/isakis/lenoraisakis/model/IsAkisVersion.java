package com.isakis.lenoraisakis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isakis.lenoraisakis.model.core.BaseModel;
import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IsAkisVersion extends BaseModel {
    @Column(name = "ISAKIS_TANIM_OID", length = 100)
    private String isakis_tanim_oid;

    private  Integer versiyon;

    private String aciklama;

    @Column(name = "olusturan_oid", length = 100)
    private String olusturan_oid;

    @Column(name = "olusturma_zamanı", length = 100)
    private String olusturma_zamanı;

    @Column(name = "isakis_tanim_adi", length = 100)
    private String isakis_tanim_adi;

    private  Integer aktif;

    @Column(name = "eklenti", length = 400)
    private String eklenti;

    private  Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "is_akis_version_id")
    @JsonIgnore
    private İsAkisTanim isakistanim;
}
