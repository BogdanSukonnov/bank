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

    public AccountDto addAccount(NewAccountDto newAccountDto) {
        log.debug("addAccount({})", newAccountDto);
        userService.getUserByIdOrThrow(newAccountDto.getUserId(), "new account");
        Account account = accountConverter.toModel(newAccountDto, UUID.randomUUID());
        account = accountRepository.save(account);
        log.debug("new account {}", account);
        AccountOfUser accountOfUser = accountConverter.toAccountOfUser(account);
        accountOfUserRepository.save(accountOfUser);
        return accountConverter.toDto(account);
    }

    public void deleteAccount(UUID accountId) {
        log.debug("deleteAccount({})", accountId);
        Account account = account(accountId);
        accountRepository.delete(account);
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

    public AccountDto accountDto(UUID accountId) {
        return accountConverter.toDto(account(accountId));
    }

    public Account updateBalance(Account oldAccount, long balanceInCents) {
        Account account = oldAccount.toBuilder().balanceInCents(balanceInCents).build();
        accountRepository.save(account);
        AccountOfUser accountOfUser = accountConverter.toAccountOfUser(account);
        accountOfUserRepository.save(accountOfUser);
        return account;
    }
}
