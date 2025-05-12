package App.Clinica;

import App.Clinica.Menus.LoginMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(LoginMenu loginMenu) {
		return args -> {
			loginMenu.display();
		};
	}

}
