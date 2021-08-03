package br.com.mayki.APIAlurachallengebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ApiAluraChallengeBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAluraChallengeBackEndApplication.class, args);
	}

}
