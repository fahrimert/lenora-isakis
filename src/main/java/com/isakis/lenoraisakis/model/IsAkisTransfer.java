package com.isakis.lenoraisakis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isakis.lenoraisakis.model.core.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IsAkisTransfer extends BaseModel {

    //İSAKIS transferin direk isakis_adim_nosunu setlemedim
    private Integer isakis_adim_no;
    private Integer sonraki_isakis_adim_no;

    @Column(name = "komut", length = 500)
    private String komut;

    private  Integer turu;

    @Column(name = "AKTIF_ISAKIS_VERSION_OID", length = 100)
    private String isakis_versiyon_oid;

    @Column(name = "kosul", length = 2048)
    private String kosul;

    private  Integer yetkı;

    @Column(name = "eklenti", length = 4000)
    private String eklenti;

    @Column(name = "aciklama")
    private String aciklama;

    private  Integer sıra;

    @Column(name = "hedef_ad_aciklama")
    private String hedef_ad_aciklama;

    @Column(name = "kosul_aciklama")
    private String kosul_aciklama;

    private  Integer versıon;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KAYNAK_ADIM_OID")
    private IsAkisAdim kaynakAdim;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEDEF_ADIM_OID")
    private IsAkisAdim hedefAdim;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "is_akis_tanim_oid")
    @JsonIgnore
    private İsAkisTanim isAkisTanim;

}
