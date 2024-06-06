package tingeso.reports_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.reports_service.Config.FeignClientConfig;
import tingeso.reports_service.request.RequestCar;

import org.springframework.web.bind.annotation.PathVariable;



import java.util.ArrayList;


@FeignClient(value = "car-repair-service",
        contextId = "DetailsFeignClient",
        path = "/api/v2/details",
        configuration = FeignClientConfig.class)
public interface DetailsFeignClient {


    @GetMapping("/carsByType/{typeId}")
        ArrayList<RequestCar> carRepairsByType( @PathVariable ("typeId") Long typeId);

}
