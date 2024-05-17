package br.com.diniz.ListaDeTarefas;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ListaDeTarefasApplication {

	@Bean // injeção do model mapper na classe main
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ListaDeTarefasApplication.class, args);
	}

}
