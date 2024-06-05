package tingeso.carrepairservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.carrepairservice.configurations.FeignClientConfig;

import java.util.ArrayList;

import tingeso.carrepairservice.requests.RequestRepair;

@FeignClient(value = "repair-service",
        contextId = "RepairFeignClient",
        path = "/api/v2/repairs",
        configuration = FeignClientConfig.class)
public interface RepairsFeignClient {
    
        @GetMapping("/all")
        ArrayList<RequestRepair> repair();

}
