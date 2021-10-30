package com.ironhack.animalsservice.services.interfaces;

import com.ironhack.animalsservice.controller.dto.AdopterDTO;
import com.ironhack.animalsservice.dao.Animal;

import java.util.List;
import java.util.Optional;

public interface IAnimalService {
    void updateField(Long id);
    List<Animal> filterAnimals(Optional<String> type, Optional<Integer> ageMin, Optional<Integer> ageMax);
    void adoptPet(AdopterDTO adopterDTO);
}
