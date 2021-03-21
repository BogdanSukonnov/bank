package io.github.bogdansukonnov.bank.model;

import lombok.Getter;

@Getter
public enum OperationType {

    ADD(1), WITHDRAW(-1);

    private final int sign;

    OperationType(int sign) {
        this.sign = sign;
    }
}
