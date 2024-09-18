package com.CN.FitFusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CN.FitFusion.model.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long>{
    
}
