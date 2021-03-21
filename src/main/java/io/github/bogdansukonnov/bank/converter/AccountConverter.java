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

    public AccountOfUser toModel(NewAccountDto newAccountDto, UUID accountId) {
        return AccountOfUser.builder()
                .primaryKey(AccountOfUser.AccountKey.builder()
                        .userId(newAccountDto.getUserId())
                        .accountId(accountId)
                        .build())
                .balanceInCents(0L)
                .currency(newAccountDto.getCurrency())
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

    public Account toAccount(AccountOfUser accountOfUser) {
        return Account.builder()
                .accountId(accountOfUser.getPrimaryKey().getAccountId())
                .userId(accountOfUser.getPrimaryKey().getUserId())
                .currency(accountOfUser.getCurrency())
                .balanceInCents(accountOfUser.getBalanceInCents())
                .build();
    }
}
