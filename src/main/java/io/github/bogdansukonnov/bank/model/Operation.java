package io.github.bogdansukonnov.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table("operations_by_account")
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Operation {

    @PrimaryKey
    private final Operation.OperationKey primaryKey;

    @Column("operation_type")
    @CassandraType(type = CassandraType.Name.INT)
    private final OperationType operationType;

    private final String currency;

    private final long sum;

    @Column("balance_in_cents")
    private final long balanceInCents;

    @PrimaryKeyClass
    @AllArgsConstructor
    @Getter
    @ToString
    @Builder
    public static class OperationKey {
        @PrimaryKeyColumn(name = "account_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private final UUID accountId;

        @PrimaryKeyColumn(name = "operation_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        @CassandraType(type = CassandraType.Name.TIMEUUID)
        private final UUID operationId;
    }
}
