package io.github.bogdansukonnov.bank.service;

import io.github.bogdansukonnov.bank.converter.AccountConverter;
import io.github.bogdansukonnov.bank.dto.AccountDto;
import io.github.bogdansukonnov.bank.dto.NewAccountDto;
import io.github.bogdansukonnov.bank.model.Account;
import io.github.bogdansukonnov.bank.model.AccountOfUser;
import io.github.bogdansukonnov.bank.repository.AccountOfUserRepository;
import io.github.bogdansukonnov.bank.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class AccountService {
    @NonNull
    private final AccountOfUserRepository accountOfUserRepository;
    @NonNull
    private final AccountRepository accountRepository;
    @NonNull 
    private final AccountConverter accountConverter;
    @NonNull
    private final UserService userService;

    public List<AccountDto> userAccounts(UUID userId) {
        log.debug("userAccounts({})", userId);
        return accountOfUserRepository.getAllAccountsByUserId(userId).stream()
                .map(accountConverter::toDto)
                .collect(Collectors.toList());
    }

    public AccountDto account(UUID userId, UUID accountId) {
        log.debug("account({}, {})", userId, accountId);
        return accountConverter.toDto(accountInternal(userId, accountId));
    }


    public AccountDto addAccount(NewAccountDto newAccountDto) {
        log.debug("addAccount({})", newAccountDto);
        userService.getUserByIdOrThrow(newAccountDto.getUserId(), "new account");
        AccountOfUser accountOfUser = accountConverter.toModel(newAccountDto, UUID.randomUUID());
        userService.getUserByIdOrThrow(newAccountDto.getUserId(), "to create account");
        accountOfUser = accountOfUserRepository.save(accountOfUser);
        log.debug("new account of user{}", accountOfUser);
        accountRepository.save(accountConverter.toAccount(accountOfUser));
        return accountConverter.toDto(accountOfUser);
    }

    public void deleteAccount(UUID userId, UUID accountId) {
        log.debug("deleteAccount({}, {})", userId, accountId);
        AccountOfUser accountOfUser = accountInternal(userId, accountId);
        accountOfUserRepository.delete(accountOfUser);
    }

    private AccountOfUser accountInternal(UUID userId, UUID accountId) {
        Optional<AccountOfUser> optionalAccount = accountOfUserRepository.findById(
                AccountOfUser.AccountKey.builder()
                        .userId(userId)
                        .accountId(accountId)
                        .build());
        if (optionalAccount.isEmpty()) {
            String errorMsg = String.format("Can't find account with userId - %s and account id - %s",
                    userId, accountId);
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return optionalAccount.get();
    }

    public Account account(UUID accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            String errorMsg = String.format("Can't find account with account id - %s", accountId);
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return optionalAccount.get();
    }
}
