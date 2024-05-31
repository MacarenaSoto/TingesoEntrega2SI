package tingeso.carrepairservice.clients;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.carrepairservice.configurations.FeignClientConfig;

import tingeso.carrepairservice.requests.RequestBrand;


@FeignClient(value = "car-service",
        contextId = "BrandsFeignClient",
        path = "/api/v2/brands",
        configuration = FeignClientConfig.class)


public interface BrandsFeignClient {

    @GetMapping("/all")
    ArrayList<RequestBrand> brand();

}
