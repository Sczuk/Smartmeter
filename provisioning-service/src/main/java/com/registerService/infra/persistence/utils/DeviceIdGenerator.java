package com.registerService.infra.persistence.utils;

import com.registerService.infra.persistence.device.DeviceEntity;
import com.registerService.infra.persistence.smartmeter.SmartmeterEntity;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DeviceIdGenerator implements IdentifierGenerator {

    ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public Object generate(SharedSessionContractImplementor session, Object entity) {
        DeviceEntity deviceEntity = (DeviceEntity) entity;
        String smartmeterId = deviceEntity.getSmartmeter().getId();

        String[] parts = smartmeterId.split("-");
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }
        return generate()+"-"+result[2];

    }

    private String generate() {
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            id.append("-")
                    .append(random.nextInt(1000, 10000));
        }

        return id.substring(1);
    }


}
