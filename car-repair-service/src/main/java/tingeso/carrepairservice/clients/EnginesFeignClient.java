package tingeso.carrepairservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.carrepairservice.configurations.FeignClientConfig;

import java.util.ArrayList;

import tingeso.carrepairservice.requests.RequestEngine;

@FeignClient(value = "car-service",
        contextId = "EnginesFeignClient",
        path = "/api/v2/engines",
        configuration = FeignClientConfig.class)

public interface EnginesFeignClient {
    
        @GetMapping("/all")
        ArrayList<RequestEngine> engine();

}
