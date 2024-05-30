package tingeso.carrepairservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

import tingeso.carrepairservice.requests.RequestCar;
import tingeso.carrepairservice.configurations.FeignClientConfig;

@FeignClient(value = "car-service",
        path = "/api/v2/cars",
        configuration = FeignClientConfig.class)


public interface CarsFeignClient {

    @GetMapping("/all")
    ArrayList<RequestCar> car();

}
