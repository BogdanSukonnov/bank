package io.github.bogdansukonnov.bank.service;

import io.github.bogdansukonnov.bank.converter.AccountConverter;
import io.github.bogdansukonnov.bank.dto.NewAccountDto;
import io.github.bogdansukonnov.bank.model.Account;
import io.github.bogdansukonnov.bank.repository.AccountUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class AccountService {
    @NonNull
    private final AccountUserRepository accountUserRepository;
    @NonNull 
    private final AccountConverter accountConverter;
    @NonNull
    private final UserService userService;

    public List<Account> userAccounts(String userId) {
        log.debug("userAccounts({})", userId);
        return accountUserRepository.getAllAccountsByUserId(userId);
    }

    public Account account(String userId, String accountId) {
        log.debug("account({}, {})", userId, accountId);
        return accountInternal(userId, accountId);
    }


    public Account addAccount(NewAccountDto newAccountDto) {
        log.debug("addAccount({})", newAccountDto);
        Account account = accountConverter.toModel(newAccountDto, UUID.randomUUID().toString());
        userService.getUserByIdOrThrow(newAccountDto.getUserId(), "to create account");
        account = accountUserRepository.save(account);
        log.debug("new account {}", account);
        return account;
    }

    public void deleteAccount(String userId, String accountId) {
        log.debug("deleteAccount({}, {})", userId, accountId);
        Account account = accountInternal(userId, accountId);
        accountUserRepository.delete(account);
    }

    private Account accountInternal(String userId, String accountId) {
        Optional<Account> optionalAccount = accountUserRepository.findById(
                Account.AccountKey.builder()
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
}
