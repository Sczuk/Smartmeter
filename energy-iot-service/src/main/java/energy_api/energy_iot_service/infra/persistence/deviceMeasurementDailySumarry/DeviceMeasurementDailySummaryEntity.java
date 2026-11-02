package energy_api.energy_iot_service.infra.persistence.deviceMeasurementDailySumarry;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "devices_measurements_daily_summary")
@Getter
@Setter
public class DeviceMeasurementDailySummaryEntity {

    @Id
    private ObjectId id;

    @Field("device_id")
    private String deviceId;

    @Field("smartmeter_id")
    private String smartmeterId;

    @Field("total_energy_KWH")
    private double totalEnergyKWH;

    @Field("current_a")
    private double averageCurrentA;

    @Field("power_w")
    private double averagePowerW;

    @Field("sample_count")
    private int sampleCount;

    private Instant date;

}
