package energy_api.energy_iot_service.infra.web.measurements.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumptionDailyResponse {

    private String date;

    private double kwh;

}
