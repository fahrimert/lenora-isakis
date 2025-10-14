    package com.isakis.lenoraisakis.model;

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
    public class İsAkisTanim extends BaseModel {
        private  String adı;
        private  String aciklama;
        private Integer aktif;
        private Integer durum;
        private Integer aktıfversıyon;

        @Column(name = "LOKASYON_OİD", length = 100)
        private String lokasyon_oid;

        @Column(name = "AKTIF_ISAKIS_VERSIYON_OID", length = 100)
        private String aktif_isakis_versiyon_oid;

        @Column(name = "SAHIP_OİD", length = 100)
        private String sahip_oid;

        @OneToMany(mappedBy = "isakistanim",cascade = CascadeType.ALL)
        private List<IsAkisAdim> i̇sAkisAdims ;

        @OneToMany(mappedBy = "isakistanim",cascade = CascadeType.ALL)
        private List<IsAkisVersion> i̇sAkisVersions ;


        @OneToMany(mappedBy = "isAkisTanim",cascade = CascadeType.ALL)
        private List<IsAkisTransfer> isakisTransfers ;



        private Integer version;

    }
