package io.github.bogdansukonnov.bank.converter;

import io.github.bogdansukonnov.bank.dto.NewOperationDto;
import io.github.bogdansukonnov.bank.dto.OperationDto;
import io.github.bogdansukonnov.bank.model.Operation;
import io.github.bogdansukonnov.bank.service.MoneyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationConverter {

    @NonNull
    private final MoneyService moneyService;

    public Operation toModel(NewOperationDto newOperationDto, UUID operationId, Long balanceInCents) {
        return Operation.builder()
                .primaryKey(Operation.OperationKey.builder()
                        .accountId(newOperationDto.getAccountId())
                        .operationId(operationId)
                        .build())
                .operationType(newOperationDto.getOperationType())
                .currency(newOperationDto.getCurrency())
                .sum(moneyService.toCents(newOperationDto.getSum()))
                .balanceInCents(balanceInCents)
                .build();
    }

    public OperationDto toDto(Operation operation) {
        return OperationDto.builder()
                .accountId(operation.getPrimaryKey().getAccountId())
                .operationType(operation.getOperationType())
                .currency(operation.getCurrency())
                .balance(moneyService.toDouble(operation.getBalanceInCents()))
                .sum(moneyService.toDouble(operation.getSum()))
                .build();
    }
}
