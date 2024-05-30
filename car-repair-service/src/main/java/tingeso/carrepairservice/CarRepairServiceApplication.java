package tingeso.carrepairservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CarRepairServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRepairServiceApplication.class, args);
	}

}
