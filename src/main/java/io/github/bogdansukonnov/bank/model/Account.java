package io.github.bogdansukonnov.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@Table("accounts_by_user")
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Account {

    @PrimaryKey
    private final AccountKey primaryKey;

    private final String currency;

    @Column("balance_in_cents")
    private final long balanceInCents;

    @PrimaryKeyClass
    @AllArgsConstructor
    @Getter
    @ToString
    @Builder
    public static class AccountKey {
        @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private final String userId;

        @PrimaryKeyColumn(name = "account_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private final String accountId;
    }
}
