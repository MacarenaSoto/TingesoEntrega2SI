package tingeso.repairservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RepairServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepairServiceApplication.class, args);
	}

}
