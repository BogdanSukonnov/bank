package io.github.bogdansukonnov.bank.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserNoIdDto {

    private final String firstName;

    private final String lastName;
}
