package com.registerService.infra.persistence.smartmeter;

import com.registerService.infra.persistence.device.DeviceEntity;
import com.registerService.infra.persistence.user.UserEntity;
import com.registerService.infra.persistence.utils.SmartmeterId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "smartmeters")
@Getter
@Setter
public class SmartmeterEntity {

    @Id
    @SmartmeterId
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, columnDefinition = "macaddr")
    @ColumnTransformer(
            read = "CAST(mac_address AS varchar)",
            write = "CAST(? AS macaddr)"
    )
    private String macAddress;

    @Column(nullable = false)
    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "smartmeter")
    private List<DeviceEntity> devices;

}
