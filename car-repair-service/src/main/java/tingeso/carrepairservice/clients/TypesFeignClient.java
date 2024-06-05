package tingeso.carrepairservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.carrepairservice.configurations.FeignClientConfig;
import tingeso.carrepairservice.requests.RequestType;

import java.util.ArrayList;

@FeignClient(value = "car-service",
        contextId = "TypesFeignClient",
        path = "/api/v2/types",
        configuration = FeignClientConfig.class)
public interface TypesFeignClient {
    
        @GetMapping("/all")
        ArrayList<RequestType> type();

}
