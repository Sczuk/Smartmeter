package energy_api.energy_iot_service.application.useCases.deviceMeasurement;

import java.time.Instant;
import java.util.List;

public interface SumPower {

    List<SumPowerRecord> execute(String smartmeterId, String userId, int hours);

    record SumPowerRecord(Instant at, double power) {}
}


