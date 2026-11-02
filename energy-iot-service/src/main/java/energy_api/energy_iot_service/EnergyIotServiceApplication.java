package energy_api.energy_iot_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@IntegrationComponentScan
@EnableScheduling
public class EnergyIotServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(EnergyIotServiceApplication.class, args);
	}

}
