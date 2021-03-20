package io.github.bogdansukonnov.bank.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor
public class NewAccountDto {

    @NonNull
    private final String userId;

    @NonNull
    private final String currency;
}
