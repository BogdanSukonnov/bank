package io.github.bogdansukonnov.bank.converter;

import io.github.bogdansukonnov.bank.dto.NewAccountDto;
import io.github.bogdansukonnov.bank.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public Account toModel(NewAccountDto newAccountDto, String accountId) {
        return Account.builder()
                .primaryKey(Account.AccountKey.builder()
                        .userId(newAccountDto.getUserId())
                        .accountId(accountId)
                        .build())
                .balanceInCents(0L)
                .currency(newAccountDto.getCurrency())
                .build();
    }
}
