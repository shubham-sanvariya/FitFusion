package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.repository.ExerciseRepository;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }
}
