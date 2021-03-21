package io.github.bogdansukonnov.bank.converter;

import io.github.bogdansukonnov.bank.dto.AccountDto;
import io.github.bogdansukonnov.bank.dto.NewAccountDto;
import io.github.bogdansukonnov.bank.model.Account;
import io.github.bogdansukonnov.bank.model.AccountOfUser;
import io.github.bogdansukonnov.bank.service.MoneyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountConverter {

    @NonNull
    private final MoneyService moneyService;

    public Account toModel(NewAccountDto newAccountDto, UUID accountId) {
        return Account.builder()
                .userId(newAccountDto.getUserId())
                .accountId(accountId)
                .balanceInCents(0L)
                .currency(newAccountDto.getCurrency())
                .build();
    }

    public AccountDto toDto(Account account) {
        return AccountDto.builder()
                .userId(account.getUserId())
                .accountId(account.getAccountId())
                .currency(account.getCurrency())
                .balance(moneyService.toDouble(account.getBalanceInCents()))
                .build();
    }

    public AccountDto toDto(AccountOfUser accountOfUser) {
        return AccountDto.builder()
                .userId(accountOfUser.getPrimaryKey().getUserId())
                .accountId(accountOfUser.getPrimaryKey().getAccountId())
                .currency(accountOfUser.getCurrency())
                .balance(moneyService.toDouble(accountOfUser.getBalanceInCents()))
                .build();
    }

    public AccountOfUser toAccountOfUser(Account account) {
        return AccountOfUser.builder()
                .primaryKey(AccountOfUser.AccountKey.builder()
                        .accountId(account.getAccountId())
                        .userId(account.getUserId())
                        .build())
                .currency(account.getCurrency())
                .balanceInCents(account.getBalanceInCents())
                .build();
    }
}
