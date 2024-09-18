package com.CN.FitFusion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.CN.FitFusion.model.Diet;
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
}
