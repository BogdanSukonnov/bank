package io.github.bogdansukonnov.bank.dto;

import io.github.bogdansukonnov.bank.model.OperationType;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@RequiredArgsConstructor
public class NewOperationDto {

    @NonNull
    private final UUID accountId;

    @NonNull
    private final OperationType operationType;

    @NonNull
    private final String currency;

    @NonNull
    private final Double sum;
}
