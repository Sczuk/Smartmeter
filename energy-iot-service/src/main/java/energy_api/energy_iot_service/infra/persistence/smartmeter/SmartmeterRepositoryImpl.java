package energy_api.energy_iot_service.infra.persistence.smartmeter;

import energy_api.energy_iot_service.domain.model.Smartmeter;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;
import energy_api.energy_iot_service.infra.persistence.exception.NotPossibleReturnListSmartmeterException;
import energy_api.energy_iot_service.infra.persistence.exception.SmartmeterNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SmartmeterRepositoryImpl implements SmartmeterRepository {

    private final SmartmeterMongoRepository repository;

    private final SmartmeterMongoMapper mapper;

    public SmartmeterRepositoryImpl(SmartmeterMongoRepository repository, SmartmeterMongoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Smartmeter save(Smartmeter smartmeter) {
        return mapper.toDomain(repository.save(mapper.toEntity(smartmeter)));
    }

    @Override
    public Smartmeter findByMacAddress(String macAddress) {
        return mapper.toDomain(repository.findByMacAddress(macAddress).orElseThrow(()-> new SmartmeterNotFoundException("smartmeter by "+macAddress+" not found")));
    }

    @Override
    public void deleteByMacAddress(String macAddress) {
        repository.deleteByMacAddress(macAddress);
    }

    @Override
    public Optional<Smartmeter> findById(String id) {
        return Optional.ofNullable(mapper.toDomain(repository.findById(id).orElseThrow(
                ()->new SmartmeterNotFoundException("smartmeter by "+id+" id not found"))));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public List<Smartmeter> findAll(){
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Smartmeter> findByActive( boolean active) {
        return repository.findByActive(active)
                .orElseThrow(() -> new NotPossibleReturnListSmartmeterException("list not returnd"))
                .parallelStream()
                .map(mapper::toDomain)
                .toList();
    }
}
