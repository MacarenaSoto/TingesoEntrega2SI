package tingeso.carrepairservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.carrepairservice.configurations.FeignClientConfig;

import java.util.ArrayList;

import tingeso.carrepairservice.requests.RequestBonus;

@FeignClient(value = "repair-service",
        contextId = "BonusFeignClient",
        path = "/api/v2/bonus",
        configuration = FeignClientConfig.class)
public interface BonusFeignClient {

    @GetMapping("/all")
    ArrayList<RequestBonus> bonus();

}
