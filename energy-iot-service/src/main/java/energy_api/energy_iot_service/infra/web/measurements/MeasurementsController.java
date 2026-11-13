package energy_api.energy_iot_service.infra.web.measurements;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.ConsumptionDaily;
import energy_api.energy_iot_service.application.useCases.deviceMeasurement.ListLatestMeasurements;
import energy_api.energy_iot_service.application.useCases.deviceMeasurement.SumPower;
import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;
import energy_api.energy_iot_service.infra.web.measurements.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/energy-iot/measurements")
public class MeasurementsController {

    private final MeasurementsMapper mapper;

    private final ListLatestMeasurements listLatestMeasurements;

    private final SumPower sumPower;

    private final ConsumptionDaily consumptionDaily;

    public MeasurementsController(MeasurementsMapper mapper, ListLatestMeasurements listLatestMeasurements, SumPower sumPower, ConsumptionDaily consumptionDaily) {
        this.mapper = mapper;
        this.listLatestMeasurements = listLatestMeasurements;
        this.sumPower = sumPower;
        this.consumptionDaily = consumptionDaily;
    }

    @GetMapping("/smartmeter/{smartmeterId}/devices/latest")
    public ResponseEntity<MeasurementsResponse> latestMeasurements(@PathVariable String smartmeterId, Authentication auth){
        List<DeviceMeasurementsResponse> measurementsResponses = new ArrayList<>();
        for(DeviceMeasurement measurement : listLatestMeasurements.execute(smartmeterId, auth.getName())){
            measurementsResponses.add(mapper.toDeviceMeasurementsResponse(measurement));
        }
        return ResponseEntity.ok(new MeasurementsResponse(measurementsResponses));
    }

    @GetMapping("/smartmeter/{smartmeterId}/power")
    public ResponseEntity<List<SumPowerWResponse>> sumPowerW(@RequestParam(name="hours", required = false, defaultValue = "48") int hours, @PathVariable String smartmeterId, Authentication auth){
        List<SumPower.SumPowerRecord> powerRecords = sumPower.execute(smartmeterId, auth.getName(), hours);
        List<SumPowerWResponse> responses = powerRecords.stream()
                .map(mapper::toSumPowerWResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/smartmeter/{smartmeterId}/consumption/daily")
    public ResponseEntity<List<ConsumptionDailyResponse>> consumptionDaily(@RequestParam(name="from" ) String from,
                                                                     @RequestParam(name="to" ) String to,
                                                                     @RequestParam(name="deviceId", required = false) String deviceId,
                                                                     @PathVariable String smartmeterId, Authentication auth){
        List<DeviceMeasurementDailySummary> dailySummaries = consumptionDaily.execute(deviceId, smartmeterId, auth.getName(), from, to);
        List<ConsumptionDailyResponse> responses = dailySummaries.stream()
                        .map(mapper::toConsumptionDailyResponse)
                        .toList();
        return ResponseEntity.ok(responses);

    }


}