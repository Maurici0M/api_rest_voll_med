package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	//necessário adicionar isso, para que o
	@Configuration
	public class AppConfig {

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();  // Cria um novo RestTemplate
		}
	}

}
