package com.isakis.lenoraisakis.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isakis.lenoraisakis.model.core.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IsAkisAdim extends BaseModel {
    //sequence flow order adımların sırası bpmnde oklarla atıyorum 1. şu 2. şu diyoruz ya o
    private Integer adim_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "is_akis_tanim_id")
    @JsonIgnore
    private İsAkisTanim isakistanim; //process definition


    //birim tipi diye bişey daha var onun da idsi
    @Column(name = "BIRIM_TIPI_OID", length = 100)
    private String birim_tipi_oid;

    //görevi yapacak birim grubunun idsi
    @Column(name = "BIRIM_OID", length = 100)
    private String birim_oid;


    @Column(name = "ısakıs_versıon_oid", length = 24)
    private String ısakıs_versıon_oid; //process definition version


    //bitis taskı mı değil mi 0 veya 1
    private Integer baslangıc;

    //türlerde hangi ıntegerlar hangi türleri temsil ediyor bilmiyorum ben böyle yaptı
    //bpmn elementleri yani iş adımlarındaki türlerde camundayı referns ldım
    // 0 -> camundadaki start event bunla başlattım setlerken böyle setledim
    //1 -> User task olarak geçiyor
    //2 ->Service Task
    //3 ->Script Task -> Camunda içinde javascript  groovy script çalıştırıyormuş.
    //4 Exclusive Gateway xor yerine geçiyor koşul kısmı böyle evreye giriyor
    //5 parallel gateway diye birşey varmış aynı anda birden fazla akış çıkartıyormuş ortaya bu and logical operatörü yerine geçiyor
    //6 Or lgoical operatörü yerine geçiyorbir veya daha fazla koşula bağı olarak çalışıyor.
    //2 tane daha var şuanlık gerek yok bizim uygulamada end event yok çünkü ona dair mantığı model yapısından çıkartamadım

    private Integer turu;


    @Column(name = "ISAKIS_GRUP_OID", length = 100)
    private String isakis_grup_oid;

    //sequence flow conditionları
    private String kosul;


    //görevi yapacak kişi
    @Column(name = "user_oid", length = 100)
    private String kullanıcı_oid;

    @Column(name = "GOREVLER", length = 32)
    private String gorevler;

    //bi sonraki adım idsi
    private Integer adim_no_referans;
    //bi önceki adımın idsi
    private Integer adim_no_geri;

    //sonra girilebilir buna
    private Integer yetkı;

    @Column(name = "ACIKLAMA", length = 256)
    //taskın açıklaması
    private String acıklama;

    //formla gönderilen eklenti bu işte servis tasksa şunu çalıştır tarzında
    @Column(name = "EK", length = 512)
    private String ek;


    //taskın açıklaması
    @Column(name = "AKIS_ACIKLAMA", length = 256)
    private String akıs_acıklama;

    @Column(name = "AKIS_SONISLEMGUN")

    private Integer akıs_sonıslemgun;
    private Integer version;

    //service taskda email gönder diyorsa email classının konumu gibi düşünmek lazım
    @Column(name = "EKLENTI", length = 2000)
    private String eklenti;

    @OneToMany(mappedBy = "kaynakAdim", cascade = CascadeType.ALL)
    private List<IsAkisTransfer> gelenAkış;

    // Bir element'e GİREN flow'lar (incoming)
    @OneToMany(mappedBy = "hedefAdim", cascade = CascadeType.ALL)
    private List<IsAkisTransfer> gidenAkış;


    //bu service taskla alakalı veya script taskla falan
    @Column(name = "SISTEM", length = 8000)
    private String sıstem;



}
