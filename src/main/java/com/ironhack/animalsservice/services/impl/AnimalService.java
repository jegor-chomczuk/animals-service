package com.ironhack.animalsservice.services.impl;


import com.ironhack.animalsservice.controller.dto.AdopterDTO;
import com.ironhack.animalsservice.controller.impl.AnimalRepository;
import com.ironhack.animalsservice.dao.Animal;
import com.ironhack.animalsservice.enums.Type;
import com.ironhack.animalsservice.proxy.AdopterServiceProxy;
import com.ironhack.animalsservice.services.interfaces.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements IAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AdopterServiceProxy adopterServiceProxy;

    public List<Animal> filterAnimals(Optional<String> type, Optional<Integer> ageMin, Optional<Integer> ageMax) {
        Integer actualAgeMin;
        Integer actualAgeMax;

        if (!ageMin.isPresent()) {
            actualAgeMin = 0;
        } else {
            actualAgeMin = ageMin.get();
        }

        if (!ageMax.isPresent()) {
            actualAgeMax = 100;
        } else {
            actualAgeMax = ageMax.get();
        }

        if (type.isPresent()) {
            return animalRepository.findByTypeAndAgeBetween(Type.valueOf(type.get().toUpperCase()), actualAgeMin, actualAgeMax);
        } else {
            return animalRepository.findByAgeBetween(actualAgeMin, actualAgeMax);
        }
    }

    public void updateField(Long id) {
        Optional<Animal> storedAnimal = animalRepository.findById(id);
        if (storedAnimal.isPresent()) {
            try {
                if(storedAnimal.get().isAvailable() == true){
                    storedAnimal.get().setAvailable(false);
                } else{
                    storedAnimal.get().setAvailable(true);
                }
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
            }

            animalRepository.save(storedAnimal.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The animal with a given id does not exist");
        }
    }

    public void adoptPet(AdopterDTO adopterDTO){
        updateField(adopterDTO.getPetId());
        adopterServiceProxy.addAdopter(adopterDTO);
    }
}