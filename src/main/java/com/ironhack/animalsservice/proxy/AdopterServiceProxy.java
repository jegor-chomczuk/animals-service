package com.ironhack.animalsservice.proxy;

import com.ironhack.animalsservice.controller.dto.AdopterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("adopter-service")
public interface AdopterServiceProxy {

    @PostMapping("/adopter")
    void addAdopter(@RequestBody AdopterDTO adopterDTO);
}
