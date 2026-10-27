package com.registerService.infra.integration.smartmeter;

import com.registerService.infra.integration.smartmeter.dto.SmartmeterCreatedResquest;
import com.registerService.infra.integration.smartmeter.dto.SmartmeterDeletedResquest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/energy-iot/smartmeters")
public interface SmartmeterClient {

    @PostExchange("/smartmeterCreated")
    void smartmeterCreated(@RequestBody SmartmeterCreatedResquest request);

    @DeleteExchange("/smartmeterDeleted/{id}")
    void smartmeterDeleted(@PathVariable String id);
}
