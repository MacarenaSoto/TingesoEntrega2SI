package tingeso.carrepairservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@FeignClient(value = "car-service",
        path = "/api/v2/cars/all")


public interface CarsFeignClient {

    @GetMapping("/probando")
    ArrayList<Object> car(@RequestBody Object object);

}
