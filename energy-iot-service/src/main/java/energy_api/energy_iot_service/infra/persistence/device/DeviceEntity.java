package energy_api.energy_iot_service.infra.persistence.device;

import energy_api.energy_iot_service.domain.enums.DeviceType;
import energy_api.energy_iot_service.infra.persistence.smartmeter.SmartmeterEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection = "devices")
@Getter
@Setter
public class DeviceEntity {

    @Id
    private String id;

    private String name;

    private DeviceType type;

    private SmartmeterEntity smartmeter;

}
