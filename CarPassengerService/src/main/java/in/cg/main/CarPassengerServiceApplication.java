package in.cg.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "in.cg.feign")
@EnableJpaRepositories(basePackages = "in.cg.repositories")
@EntityScan(basePackages = "in.cg.model")
public class CarPassengerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarPassengerServiceApplication.class, args);
	}

}
