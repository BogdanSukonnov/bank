package io.github.bogdansukonnov.bank.controller;

import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RequestMapping("api/users")
public class UserController {

    @NonNull
    private final UserService userService;

    @GetMapping()
    public List<UserDto> users() {
        log.debug("users()");
        return userService.users();
    }

    @GetMapping("/{id}")
    public UserDto user(@PathVariable String id) {
        log.debug("users({})", id);
        return userService.user(id);
    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDto userDto) {
        log.debug("users({})", userDto);
        return userService.addUser(userDto);
    }
}
