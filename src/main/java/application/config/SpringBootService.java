package application.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "application")
@EntityScan(basePackages = "infrastructure")
public class SpringBootService {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootService.class, args);
  }

}
