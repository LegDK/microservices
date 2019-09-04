package ru.maltsev.api.users.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maltsev.api.users.domain.CreateUserRequest;
import ru.maltsev.api.users.domain.CreateUserResponse;
import ru.maltsev.api.users.dto.UserDto;
import ru.maltsev.api.users.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    UserService userService;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port" + env.getProperty("local.server.port") + "token=" + env.getProperty("token.secret");
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);

        CreateUserResponse returnValue = modelMapper.map(createdUser, CreateUserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

    }

}
