package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.exception.DietNotFoundException;
import com.CN.FitFusion.exception.UserNotFoundException;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.DietRepository;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class DietService {
    
    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Diet> getAllDiets(){
        return dietRepository.findAll();
    }

    public Diet getDietById(Long id){
        return dietRepository.findById(id)
            .orElseThrow(() -> new DietNotFoundException("diet not found"));
    }

    public void addDietForUser(DietDto dietDto, Long userId){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("user not found"));

        Diet diet =  Diet.builder()
                        .name(dietDto.getName())
                        .description(dietDto.getDescription())
                        .build();
        
        user.getDiets().add(diet);
        diet.setUser(user);

        dietRepository.save(diet);
        userRepository.save(user);
    }

    public void updateDiet(DietDto dietDto, Long id){
        Diet diet = getDietById(id);

        diet.setName(dietDto.getName());
        diet.setDescription(dietDto.getDescription());

        dietRepository.save(diet);
    }
}
