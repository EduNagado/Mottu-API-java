package Mottu.com.GEF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GefApplication {

	public static void main(String[] args) {
		SpringApplication.run(GefApplication.class, args);
		System.out.println("Bem Vindo ao GEF!");
	}

}
