package com.registerService.infra.persistence.device;

import com.registerService.domain.model.enums.DeviceType;
import com.registerService.infra.persistence.smartmeter.SmartmeterEntity;
import com.registerService.infra.persistence.utils.DeviceId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;

@Entity
@Table(name = "devices")
@Getter
@Setter
public class DeviceEntity {

    @Id
    @DeviceId
    private String id;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "device_type")
    private DeviceType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name = "smartmeter_id")
    private SmartmeterEntity smartmeter;

}
