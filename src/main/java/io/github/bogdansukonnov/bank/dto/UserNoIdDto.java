package io.github.bogdansukonnov.bank.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor
public class UserNoIdDto {
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
}
