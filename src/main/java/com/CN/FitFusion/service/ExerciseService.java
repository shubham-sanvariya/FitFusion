package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.exception.ExerciseNotFoundException;
import com.CN.FitFusion.exception.UserNotFoundException;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.ExerciseRepository;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }

    public Exercise getExercisesById(Long id){
        return exerciseRepository.findById(id)
            .orElseThrow(() -> new ExerciseNotFoundException("exercise not found"));
    }

    public void addExerciseForUser(ExerciseDto exerciseDto, Long userId){
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("user not found"));

        Exercise exercise = Exercise.builder().name(exerciseDto.getName())
                            .description(exerciseDto.getDescription())
                            .sets(exerciseDto.getSets())
                            .reps(exerciseDto.getReps())
                            .build();
        user.getExerciseList().add(exercise);
        exerciseRepository.save(exercise);
        userRepository.save(user);
    }

    public void updateExercise(ExerciseDto exerciseDto, Long id){
        Exercise exercise = getExercisesById(id);

        exercise.setName(exerciseDto.getName());
        exercise.setDescription(exerciseDto.getDescription());
        exercise.setSets(exerciseDto.getSets());
        exercise.setReps(exerciseDto.getReps());

        exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id){
        exerciseRepository.deleteById(id);
    }
}
