package com.CN.FitFusion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.service.DietService;

@RestController
@RequestMapping("/diet")
public class DietController {
    
    @Autowired
    private DietService dietService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.OK)
    public List<Diet> getAllDiets(){
        return dietService.getAllDiets();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.OK)
    public Diet getDietById(@PathVariable Long id){
        return dietService.getDietById(id);
    }

    @PostMapping("/create/{userId}")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDietForUser(@RequestBody DietDto dietDto, @PathVariable Long userId){
        dietService.addDietForUser(dietDto,userId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.OK)
    public void updateDiet(@RequestBody DietDto dietDto, @PathVariable Long id){
        dietService.updateDiet(dietDto,id);
    }
}
