package com.CN.FitFusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CN.FitFusion.model.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet,Long>{
    
}
