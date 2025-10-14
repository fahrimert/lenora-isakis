package com.isakis.lenoraisakis.model.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isakis.lenoraisakis.config.OIDGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(generator="oid")
    @GenericGenerator(name="oid", type = OIDGenerator.class)
    @Column(name = "OID", length = 24)
    private String oid;

    @CreatedDate
    @Column(name = "CREATED_TIME")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private LocalDateTime created;

    @CreatedBy
    @Column(name = "CREATOR_PERSON")
    private String creatorPerson;

    @LastModifiedDate
    @Column(name = "LAST_UPDATED")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private LocalDateTime lastUpdated;

    @LastModifiedBy
    @Column(name = "LAST_UPDATER")
    private String lastUpdater;

    @Column(name = "STATUS", length = 1)
    private String status = "1";

    @Column(name = "VERSION", length = 1)
    private Integer version = 0;

    @Transient
    private String creatorData;

    @Transient
    private String updaterData;
}
