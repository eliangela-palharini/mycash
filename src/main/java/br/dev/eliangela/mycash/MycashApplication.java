package br.dev.eliangela.mycash;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.eliangela.mycash.service.UsuarioService;

@SpringBootApplication
@RestController
public class MycashApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MycashApplication.class, args);
		
		UsuarioService service = context.getBean(UsuarioService.class);
		service.registraUsuarioAdmin("admin@mycash.com", "admin");
		service.save("user@mycash.com", "123456");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@GetMapping("/ola")
	public String ola(@RequestParam("nome") String nome) {
		return "Olá, " + nome + "!!!!";
	}

}
