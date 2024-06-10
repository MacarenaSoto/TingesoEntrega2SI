package tingeso.reports_service.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;



import tingeso.reports_service.Config.FeignClientConfig;

import tingeso.reports_service.request.RequestType;

import  java.util.ArrayList;




@FeignClient(value = "car-repair-service",
        contextId = "TypeFeignClient",
        path = "/api/v2/details",
        configuration = FeignClientConfig.class)
public interface TypeFeignClient {

    @GetMapping("/types")
        ArrayList<RequestType> types();

}
