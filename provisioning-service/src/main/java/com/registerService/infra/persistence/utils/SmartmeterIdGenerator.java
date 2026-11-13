package com.registerService.infra.persistence.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SmartmeterIdGenerator implements IdentifierGenerator {

    ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return generate();
    }


    private String generate() {
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            id.append("-")
                    .append(random.nextInt(1000, 10000));
        }

        return id.substring(1);
    }
}
