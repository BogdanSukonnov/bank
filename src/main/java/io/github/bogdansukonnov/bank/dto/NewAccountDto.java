package io.github.bogdansukonnov.bank.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class NewAccountDto {

    @NonNull
    private final UUID userId;

    @NonNull
    private final String currency;
}
