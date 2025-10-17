package com.isakis.lenoraisakis.model.runtime;


import com.isakis.lenoraisakis.model.core.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IsAkisSevk extends BaseModel {
    @Column(name = "ıslem_oid", length = 14)
    private  String ıslem_oid;
    @Column(name = "ıslem_durum_oid", length = 14)
    private String ısakıs_durum_oid;
    @Column(name = "ıslem_zamani", length = 14)
    private  String ıslem_zamani;
    @Column(name = "son_sevk")
    private  Integer son_sevk;
    @Column(name = "gonderen_kullanici_oid", length = 14)
    private  String gonderen_kullanici_oid;
    @Column(name = "gonderen_birim_oid", length = 14)
    private  String gonderen_birim_oid;
    @Column(name = "alan_kullanici_oid", length = 14)
    private  String alan_kullanici_oid;
    @Column(name = "alan_birim_oid", length = 14)
    private  String alan_birim_oid;
    private  Integer yon;
    private  Integer mevcut_durumu;
    @Column(name = "son_islem_tarihi", length = 8)
    private  String son_islem_tarihi;
    @Column(name = "son_islem_tarihi", length = 14)
    private  String isakıs_version_oid;
    private  Integer gonderen_sakıs_adim_no;
    private  Integer alan_isakıs_adım_no;
    @Column(name = "son_islem_tarihi", length = 14)
    private String vekil_kullanici_oid;
    private  Integer acıklama_turu;
    private  Integer ıslem_tipi;
    private  Integer ıslem_alt_tipi;
    private  Integer hedef_tipi;
    @Column(name = "ust_sevk_oid", length = 14)
    private  String ust_sevk_oid;
    private  Integer ust_durumu;
    private  Integer gıdıs_tipi;
    private  Integer sure_tipi;
    @Column(name = "geri_sevk_oid", length = 14)
    private String geri_sevk_oid;
    @Column(name = "son_sevk_oid", length = 14)
    private  String son_sevk_oid;
    private  Integer kılıt_durumu;
    @Column(name = "gorev_oid", length = 14)
    private String gorev_oid;
    private  Integer yetkı_tipi;
    @Column(name = "bırım_oid", length = 14)
    private  String bırım_oid;
    @Column(name = "tahmını_bitis_tarihi", length = 8)
    private  String tahmını_bitis_tarihi;
    @Column(name = "tahmini_bitis_aciklama", length = 255)
    private String tahmini_bitis_aciklama;
    @Column(name = "alma_zamani", length = 14)

    private String alma_zamani;
    @Column(name = "alt_ıslem_oid", length = 14)
    private  String alt_ıslem_oid;
    private Integer version;
    private  String aciklama;






}
