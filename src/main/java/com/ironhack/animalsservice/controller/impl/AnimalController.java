package com.ironhack.animalsservice.controller.impl;

import com.ironhack.animalsservice.controller.dto.AdopterDTO;
import com.ironhack.animalsservice.dao.Animal;
import com.ironhack.animalsservice.proxy.AdopterServiceProxy;
import com.ironhack.animalsservice.services.interfaces.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private IAnimalService animalService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> findAvailableAnimalsByTypeAndAgeRange(@RequestParam Optional<String> type, @RequestParam Optional<Integer> ageMin, @RequestParam Optional<Integer> ageMax){
        return animalService.filterAnimals(type, ageMin, ageMax);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable(name="id") Long id){
        animalService.updateField(id);
    }

    @PostMapping("/adopt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adoptPet(@RequestBody AdopterDTO adopterDTO){
        animalService.adoptPet(adopterDTO);
    }
}
