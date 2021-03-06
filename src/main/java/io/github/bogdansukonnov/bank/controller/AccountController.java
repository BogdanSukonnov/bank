package io.github.bogdansukonnov.bank.controller;

import io.github.bogdansukonnov.bank.dto.AccountDto;
import io.github.bogdansukonnov.bank.dto.NewAccountDto;
import io.github.bogdansukonnov.bank.service.AccountService;
import io.swagger.annotations.Api;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RequestMapping("api/accounts")
@Api(tags = {"Accounts"})
public class AccountController {

    @NonNull
    private final AccountService accountService;

    @GetMapping(value = "/by-user/{userId}", produces = "application/json")
    @ApiOperation("Get all accounts of given user")
    public List<AccountDto> accounts(@ApiParam("user id") @PathVariable("userId") UUID userId
            , HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), userId);
        return accountService.userAccounts(userId);
    }

    @GetMapping(value = "/{accountId}", produces = "application/json")
    @ApiOperation("Get one account")
    public AccountDto account(@ApiParam("account id") @PathVariable UUID accountId
            , HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), accountId);
        return accountService.accountDto(accountId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Save new account")
    public AccountDto addAccount(@ApiParam("new account") @Valid @RequestBody NewAccountDto newAccountDto
            , HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), newAccountDto);
        return accountService.addAccount(newAccountDto);
    }

    @DeleteMapping(value = "/{accountId}", produces = "application/json")
    @ApiOperation("Remove account")
    public void deleteAccount(@ApiParam("account id") @PathVariable UUID accountId,
                              HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), accountId);
        accountService.deleteAccount(accountId);
    }
}
