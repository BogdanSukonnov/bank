package io.github.bogdansukonnov.bank.controller;

import io.github.bogdansukonnov.bank.dto.NewUserDto;
import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public List<UserDto> users(HttpServletRequest request) {
        log.debug("{}",  request.getRequestURI());
        return userService.users();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Get one user")
    public UserDto user(@ApiParam("user id") @PathVariable String id, HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), id);
        return userService.user(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Save new user")
    public UserDto addUser(@ApiParam("new user") @Valid @RequestBody NewUserDto userNoIdDto, HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), userNoIdDto);
        return userService.addUser(userNoIdDto);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Remove user")
    public void deleteUser(@ApiParam("user id") @PathVariable String id, HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), id);
        userService.deleteUser(id);
    }
}
