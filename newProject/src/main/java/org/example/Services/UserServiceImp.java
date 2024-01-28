package org.example.Services;

import org.example.Dtos.UserRequestDto;
import org.example.Entities.User;
import org.example.Repositorys.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserRequestDto userRequestDto){
        User user=new User();
        user.setName(userRequestDto.getName());

        if(userRequestDto.getCreatedDate()!=null && userRequestDto.getCreatedDate().equals("")){
            user.setCreatedDate(userRequestDto.getCreatedDate());
        }
        else{
            user.setCreatedDate(Date.valueOf(LocalDate.now()));
        }

        if(userRequestDto.getUpdatedDate()!=null && userRequestDto.getUpdatedDate().equals("")){
            user.setUpdatedDate(userRequestDto.getUpdatedDate());
        }
        else{
            user.setUpdatedDate(Date.valueOf(LocalDate.now()));
        }

        userRepository.save(user);
    }

    @Override
    public UserRequestDto getUserById(Long id){
     Optional<User> user=  userRepository.findById(id);
     if(user.isEmpty())throw new RuntimeException("User Not Found");
     UserRequestDto userRequestDto=new ModelMapper().map(user,UserRequestDto.class);
     return userRequestDto;
    }

    @Override
    public void deleteUser(UserRequestDto userRequestDto){
        User user=new ModelMapper().map(userRequestDto, User.class);
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }


}
