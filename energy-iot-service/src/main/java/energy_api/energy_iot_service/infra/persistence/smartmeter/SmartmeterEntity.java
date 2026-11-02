package energy_api.energy_iot_service.infra.persistence.smartmeter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "smartmeters")
@Getter
@Setter
public class SmartmeterEntity {

    @Id
    private String id;

    private String name;

    @Field("user_id")
    private String userId;

    private String macAddress;

    private boolean active;

}
