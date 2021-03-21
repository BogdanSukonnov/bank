package io.github.bogdansukonnov.bank.service;

import io.github.bogdansukonnov.bank.model.OperationType;
import org.springframework.stereotype.Service;

@Service
public class MoneyService {

    public double toDouble(long cents) {
        return ((double) cents) / 100.00;
    }

    public long toCents(double sum) {
        return (long) (sum * 100.00);
    }

    public Long balanceAfterOperation(Long balanceInCents, Double sum, OperationType operationType) {
        return balanceInCents + operationType.getSign() * toCents(sum);
    }
}
