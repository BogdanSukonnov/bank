package io.github.bogdansukonnov.bank.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserDto extends UserNoIdDto {

    private final String id;
}
