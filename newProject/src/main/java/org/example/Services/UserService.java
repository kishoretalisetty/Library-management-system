package org.example.Services;

import org.example.Dtos.UserRequestDto;
import org.example.Entities.User;

public interface UserService {

    void addUser(UserRequestDto user);

    UserRequestDto getUserById(Long id);

    void deleteUser(UserRequestDto userRequestDto);

    void deleteUserById(Long userId);
}
