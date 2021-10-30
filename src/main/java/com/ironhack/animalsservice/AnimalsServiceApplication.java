package com.ironhack.animalsservice;

import com.ironhack.animalsservice.controller.impl.AnimalRepository;
import com.ironhack.animalsservice.dao.Animal;
import com.ironhack.animalsservice.enums.Type;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AnimalsServiceApplication {

	@Autowired
	private AnimalRepository animalRepository;

	public static void main(String[] args) {

		SpringApplication.run(AnimalsServiceApplication.class, args);
			}

	Animal animal1 = new Animal(1l, "Freckles", Type.DOG, 7, true);
	Animal animal2 = new Animal(2l, "Lua", Type.DOG, 1, false);
	Animal animal3 = new Animal(3l, "Baby", Type.CAT, 14, false);
	Animal animal4 = new Animal(4l, "Vinnie", Type.DOG, 5, true);

	@Bean
	InitializingBean createAnimals() {
		return () -> {
			animalRepository.saveAll(List.of(animal1, animal2, animal3, animal4));
		};
	}

}
