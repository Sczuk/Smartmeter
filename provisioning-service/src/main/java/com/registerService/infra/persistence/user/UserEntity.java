package com.registerService.infra.persistence.user;

import com.registerService.domain.model.enums.Role;
import com.registerService.infra.persistence.smartmeter.SmartmeterEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private Timestamp created_at;

    @OneToMany(mappedBy = "user")
    private List<SmartmeterEntity> smartmeters;

}