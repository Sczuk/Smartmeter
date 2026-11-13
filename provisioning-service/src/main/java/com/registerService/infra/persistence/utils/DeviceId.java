package com.registerService.infra.persistence.utils;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IdGeneratorType(DeviceIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface DeviceId {
}
