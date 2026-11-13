package energy_api.energy_iot_service.infra.persistence.deviceMeasurement;

import energy_api.energy_iot_service.domain.enums.DeviceState;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TimeSeries;
import org.springframework.data.mongodb.core.timeseries.Granularity;

import java.time.Instant;

@Document(collection = "devices_measurements")
@TimeSeries(timeField = "", metaField = "metadata", granularity = Granularity.SECONDS)
@Getter
@Setter
public class DeviceMeasurementEntity {

    @Id
    private ObjectId id;

    private Metadata metadata;

    private DeviceState state;

    @Field("power_w")
    private double powerW;

    @Field("voltage_v")
    private double voltageV;

    @Field("current_a")
    private double currentA;

    private Instant measured;

}
