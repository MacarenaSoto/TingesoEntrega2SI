package tingeso.reports_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.reports_service.Config.FeignClientConfig;
import tingeso.reports_service.request.RequestCarRepairs;



import java.util.ArrayList;

@FeignClient(value = "car-repair-service",
        contextId = "CarRepairsFeignClient",
        path = "/api/v2/carrepairs",
        configuration = FeignClientConfig.class)
public interface  CarRepairsFeignClient {
    
        @GetMapping("/all")
        ArrayList<RequestCarRepairs> carRepairs();

        @GetMapping("/carsByType/{typeId}")
        ArrayList<RequestCarRepairs> carRepairsByType(Long type);


}
