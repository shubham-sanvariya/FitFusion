package com.CN.FitFusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CN.FitFusion.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    
}
