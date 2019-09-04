package ru.maltsev.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.maltsev.api.users.dto.UserDto;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserDetailsByEmail(String email);
    UserDto getUserByUserId(String userId);
}
