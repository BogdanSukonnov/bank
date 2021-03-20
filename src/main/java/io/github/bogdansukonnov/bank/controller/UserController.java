package io.github.bogdansukonnov.bank.controller;

import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.dto.UserNoIdDto;
import io.github.bogdansukonnov.bank.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RequestMapping("api/users")
public class UserController {

    @NonNull
    private final UserService userService;

    @GetMapping(produces = "application/json")
    @ApiOperation("Get all users")
    public List<UserDto> users() {
        log.debug("users()");
        return userService.users();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Get one user")
    public UserDto user(@ApiParam("user id") @PathVariable String id) {
        log.debug("users({})", id);
        return userService.user(id);
    }

    @PostMapping(produces = "application/json")
    @ApiOperation("Save new user")
    public UserDto addUser(@ApiParam("new user") @Valid @RequestBody UserNoIdDto userNoIdDto) {
        log.debug("users({})", userNoIdDto);
        return userService.addUser(userNoIdDto);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Remove user")
    public void deleteUser(@ApiParam("user id") @PathVariable String id) {
        log.debug("users({})", id);
        userService.deleteUser(id);
    }
}
