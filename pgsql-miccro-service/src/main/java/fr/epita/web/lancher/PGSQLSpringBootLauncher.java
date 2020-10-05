package fr.epita.web.lancher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "fr.epita")
public class PGSQLSpringBootLauncher {
	
	public static void main(String[] args) {
		SpringApplication.run(PGSQLSpringBootLauncher.class, args);
		
	}
	

}
