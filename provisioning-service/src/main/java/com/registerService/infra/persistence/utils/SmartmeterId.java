package com.registerService.infra.persistence.utils;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IdGeneratorType(SmartmeterIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmartmeterId {
}
