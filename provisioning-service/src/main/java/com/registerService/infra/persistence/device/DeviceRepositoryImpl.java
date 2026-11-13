package com.registerService.infra.persistence.device;

import com.registerService.domain.exception.device.DeviceNotFoundException;
import com.registerService.domain.model.Device;
import com.registerService.domain.repository.DeviceRepository;
import com.registerService.infra.config.CycleAvoidingMappingContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    private final DeviceJpaRepository repository;

    private final DeviceJpaMapper mapper;

    public DeviceRepositoryImpl(DeviceJpaRepository repository, DeviceJpaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Device save(Device device) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

        DeviceEntity entity = repository.save(mapper.toEntity(device, context));
        return mapper.toDomain(entity,context);
    }

    @Override
    public void deleteById(String id) {
        if(!repository.existsById(id)){
        repository.findById(id).orElseThrow(()->
                new DeviceNotFoundException("device by "+id+" id not found"));
        return;
        }
        repository.deleteById(id);
    }

    @Override
    public Optional<Device> findById(String id) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        DeviceEntity entity = repository.findById(id)
                .orElseThrow(()->new DeviceNotFoundException("device by "+id+" id not found"));
        return Optional.ofNullable(mapper.toDomain(entity, context));
    }

}
