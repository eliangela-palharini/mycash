package br.dev.eliangela.mycash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
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
	}
	
	@GetMapping("/ola")
	public String ola(@RequestParam("nome") String nome) {
		return "Olá, " + nome + "!!!!";
	}

}
