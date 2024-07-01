package br.com.alura.literalura;

import br.com.alura.literalura.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json =  consumoApi.obterDados("https://gutendex.com/books");
		System.out.println(json);

		System.out.println("-----------");
		System.out.println("Escolha um número de sua opção: ");
		System.out.println(" 1- buscar livro pelo titulo ");
		System.out.println(" 2- listar livros registrados ");
		System.out.println(" 3- listar autores registrados ");
		System.out.println(" 4- listar autores vivos em um determinado ano ");
		System.out.println(" 5- listar livros em um determinado idioma ");
		System.out.println(" 6- sair");
	}

}
