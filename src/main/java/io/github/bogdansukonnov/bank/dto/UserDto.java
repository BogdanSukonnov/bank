package io.github.bogdansukonnov.bank.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UserDto {

    private final String id;

    private final String firstName;

    private final String lastName;
}
