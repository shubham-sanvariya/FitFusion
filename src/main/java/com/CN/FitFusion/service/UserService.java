package com.CN.FitFusion.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.exception.UserNotFoundException;
import com.CN.FitFusion.model.Role;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void createUser(UserDto userDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = User.builder()
            .email(userDto.getEmail())
            .password(encoder.encode(userDto.getPassword()))
            .age(userDto.getAge())
            .gender(userDto.getGender())
            .contactNo(userDto.getContactNo())
            .build();

        Role role = new Role();
        Set<Role> roles = new HashSet<>();

        if (userDto.getUserType() != null) {
            if (userDto.getUserType().equalsIgnoreCase("TRAINER")) {
                role.setRoleName("ROLE_TRAINER");
                roles.add(role);
                user.setRoles(roles);
            } else if (userDto.getUserType().equalsIgnoreCase("ADMIN")) {
                role.setRoleName("ROLE_ADMIN");
                roles.add(role);
                user.setRoles(roles);
            } else {
                role.setRoleName("ROLE_CUSTOMER");
                roles.add(role);
                user.setRoles(roles);
            }
        } else {
            role.setRoleName("ROLE_CUSTOMER");
            roles.add(role);
            user.setRoles(roles);
        }
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}
