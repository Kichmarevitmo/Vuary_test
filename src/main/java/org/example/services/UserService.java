package org.example.services;

import org.example.entities.FileData;
import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.entities.User;
import org.example.security.JwtAuthResponse;

import java.text.ParseException;

public interface UserService {
    public void updateUser(User user);
    UserDto register(UserDto userDto, FileData fileData) throws ParseException;
    UserDto register(UserDto userDto) throws ParseException;
    JwtAuthResponse login(LoginDto loginDto);


    void logout(String token);
    User getUser(String token);
    public boolean activateUser(String code);
}