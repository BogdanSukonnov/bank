package io.github.bogdansukonnov.bank.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.github.bogdansukonnov.bank.converter.OperationConverter;
import io.github.bogdansukonnov.bank.dto.NewOperationDto;
import io.github.bogdansukonnov.bank.dto.OperationDto;
import io.github.bogdansukonnov.bank.model.Account;
import io.github.bogdansukonnov.bank.model.Operation;
import io.github.bogdansukonnov.bank.model.OperationType;
import io.github.bogdansukonnov.bank.repository.OperationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class OperationService {
    @NonNull
    private final OperationRepository operationRepository;
    @NonNull
    private final OperationConverter operationConverter;
    @NonNull
    private final AccountService accountService;
    @NonNull
    private final MoneyService moneyService;

    public List<OperationDto> accountOperations(UUID accountId) {
        log.debug("accountOperations({})", accountId);
        return operationRepository.getAllOperationsByAccountId(accountId).stream()
                .map(operationConverter::toDto)
                .collect(Collectors.toList());
    }

    public OperationDto addOperation(NewOperationDto newOperationDto) {
        log.debug("addOperation({})", newOperationDto);
        Account account = accountService.account(newOperationDto.getAccountId());
        Long balanceInCents = moneyService.balanceAfterOperation(account.getBalanceInCents(), newOperationDto.getSum(),
                newOperationDto.getOperationType());
        if (newOperationDto.getOperationType().equals(OperationType.WITHDRAW) &&
                balanceInCents < moneyService.toCents(newOperationDto.getSum())) {
            String errorMsg = String.format("The account balance is not enough for operation. Balance: %,.2f, %s sum: %,.2f. %s",
                    moneyService.toDouble(balanceInCents), newOperationDto.getOperationType(),
                    newOperationDto.getSum(), newOperationDto);
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        Operation operation = operationConverter.toModel(newOperationDto, Uuids.timeBased(), balanceInCents);
        operation = operationRepository.save(operation);
        log.debug("new operation {}", operation);
        accountService.updateBalance(account, balanceInCents);
        return operationConverter.toDto(operation);
    }
}
