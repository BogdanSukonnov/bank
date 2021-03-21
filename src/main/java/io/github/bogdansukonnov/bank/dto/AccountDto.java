package io.github.bogdansukonnov.bank.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@RequiredArgsConstructor
public class AccountDto {

    @NonNull
    private final UUID userId;

    @NonNull
    private final UUID accountId;

    @NonNull
    private final String currency;

    @NonNull
    private final Double balance;
}
