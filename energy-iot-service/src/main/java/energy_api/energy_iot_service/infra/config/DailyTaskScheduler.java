package energy_api.energy_iot_service.infra.config;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.SaveMeasurementDailyScheduled;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyTaskScheduler {

    private final SaveMeasurementDailyScheduled saveMeasurementDailyScheduled;

    public DailyTaskScheduler(SaveMeasurementDailyScheduled saveMeasurementDailyScheduled) {
        this.saveMeasurementDailyScheduled = saveMeasurementDailyScheduled;
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "America/Sao_Paulo")
    public void executeDailyTask() {
        saveMeasurementDailyScheduled.execute();
    }

}
