package com.ironhack.animalsservice.controller.impl;

import com.ironhack.animalsservice.dao.Animal;
import com.ironhack.animalsservice.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByTypeAndAgeBetween(Type type, Integer ageMin, Integer ageMax);
    List<Animal> findByAgeBetween(Integer ageMin, Integer ageMax);
}
