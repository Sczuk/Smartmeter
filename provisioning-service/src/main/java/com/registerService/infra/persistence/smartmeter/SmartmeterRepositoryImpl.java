package com.registerService.infra.persistence.smartmeter;

import com.registerService.domain.exception.smartmeter.SmartmeterNotFoundException;
import com.registerService.domain.model.Smartmeter;
import com.registerService.domain.repository.SmartmeterRepository;
import com.registerService.infra.config.CycleAvoidingMappingContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SmartmeterRepositoryImpl implements SmartmeterRepository {

    private final SmartmeterJpaRepository repository;

    private final SmartmeterJpaMapper mapper;

    public SmartmeterRepositoryImpl(SmartmeterJpaRepository repository, SmartmeterJpaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Smartmeter save(Smartmeter smartmeter) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        SmartmeterEntity smartmeterEntity = mapper.toEntity(smartmeter,context);
        repository.save(smartmeterEntity);
        return mapper.toDomain(smartmeterEntity,context);
    }

    @Override
    public Optional<Smartmeter> findByMacAddress(String macAddress) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        SmartmeterEntity smartmeterEntity = repository.findByMacAddress(macAddress)
                .orElseThrow(()->new SmartmeterNotFoundException("smartmeter by "+macAddress+" mac address not found"));
        return Optional.ofNullable(mapper.toDomain(smartmeterEntity, context));
    }

    @Override
    @Transactional
    public void deleteByMacAddress(String macAddress) {
        repository.deleteByMacAddress(macAddress);
    }

    @Override
    public void deleteById(String smartmeterId) {
        repository.deleteById(smartmeterId);
    }

    @Override
    public Optional<Smartmeter> findById(String id) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        SmartmeterEntity smartmeterEntity = repository.findById(id)
                .orElseThrow(()->new SmartmeterNotFoundException("smartmeter by "+ id +" id not found"));
        return Optional.ofNullable(mapper.toDomain(smartmeterEntity, context));
    }

    @Override
    public Smartmeter findByNameAndUser_id(String name, UUID userId) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return mapper.toDomain(repository.findByNameAndUser_id(name, userId).orElseThrow(()->new SmartmeterNotFoundException("Smartmeter by name "+name+" not found")), context);
    }
}
