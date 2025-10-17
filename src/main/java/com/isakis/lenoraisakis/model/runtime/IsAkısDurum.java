package com.isakis.lenoraisakis.model.runtime;

import com.isakis.lenoraisakis.model.core.BaseModel;
import jakarta.persistence.Column;

public class IsAkısDurum extends BaseModel {
    @Column(name = "islem_oid", length = 14)
    private  String islem_oid;
    private Integer islem_tipi;
    private Integer islem_alt_tipi;
    @Column(name = "isakıs_versıyon_oid", length = 14)
    private  String isakıs_versıyon_oid;
    @Column(name = "baslatan_oıd", length = 14)
    private  String baslatan_oıd;
    @Column(name = "baslatan_birim_oid", length = 14)
    private  String baslatan_birim_oid;

    @Column(name = "baslatan_gorev_oid", length = 14)
    private  String baslatan_gorev_oid;
    private  Integer baslatan_adım_no;
    @Column(name = "baslangıc_zamani", length = 14)
    private  String baslangıc_zamani;
    @Column(name = "baslangıc_zamani", length = 8)
    private  String baslangıc_son_ıslem_tarihi;
    private Integer durum;
    private Integer sevk_durum;
    @Column(name = "bıtıs_zamani", length = 14)
    private String bıtıs_zamani;
    @Column(name = "sonbaslatan_oid", length = 14)
    private String sonbaslatan_oid;
    @Column(name = "sonbaslatan_birim_oid", length = 14)
    private String sonbaslatan_birim_oid;
    @Column(name = "sonbaslatan_gorev_oid", length = 14)
    private String sonbaslatan_gorev_oid;
    @Column(name = "sonbaslatan_adim_no")
    private Integer sonbaslatan_adim_no;
    @Column(name = "sonbaslatan_zamani", length = 14)
    private String sonbaslatan_zamani;
    @Column(name = "ıptal_eden_oıd", length = 14)
    private  String ıptal_eden_oıd;
    @Column(name = "ıptal_eden_bırım_oıd", length = 14)
    private  String ıptal_eden_bırım_oıd;
    @Column(name = "ıptal_eden_gorev_oıd", length = 14)
    private  String ıptal_eden_gorev_oıd;
    @Column(name = "sonbaslangic_zamani", length = 14)

    private  String sonbaslangic_zamani;
    @Column(name = "ıptal_zamani", length = 14)

    private  String ıptal_zamani;
    private  String acıklama;
    @Column(name = "baslatan_vekıl_oid", length = 14)

    private  String baslatan_vekıl_oid;
    private  Integer versıon;
    @Column(name = "alt_islem_oid", length = 14)

    private  String alt_islem_oid;
    @Column(name = "acıklama_sebep", length = 100   )

    private  String acıklama_sebep;





}
