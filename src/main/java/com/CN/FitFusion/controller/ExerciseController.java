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

import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.service.ExerciseService;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    
    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.OK)
    public List<Exercise> getAllExercises(){
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.OK)
    public Exercise getExerciseById(@PathVariable Long id){
        return exerciseService.getExercisesById(id);
    }

    @PostMapping("/create/{userId}")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.OK)
    public void createExerciseForUser(@RequestBody ExerciseDto exerciseDto, @PathVariable Long userId){
        exerciseService.addExerciseForUser(exerciseDto,userId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.OK)
    public void updateExercise(@RequestBody ExerciseDto exerciseDto, @PathVariable Long id){
        exerciseService.updateExercise(exerciseDto,id);
    }
}
