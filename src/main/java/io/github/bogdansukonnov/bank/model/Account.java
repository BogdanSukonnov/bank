package io.github.bogdansukonnov.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("accounts_by_user")
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Account {

    @PrimaryKey
    private final AccountKey primaryKey;

    private final String currency;

    private final long balanceInCents;

    @PrimaryKeyClass
    @AllArgsConstructor
    @Getter
    @ToString
    @Builder
    public static class AccountKey {
        @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private final String userId;

        @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private final String accountId;
    }
}
