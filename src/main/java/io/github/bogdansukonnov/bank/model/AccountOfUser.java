package io.github.bogdansukonnov.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table("accounts_by_user")
@AllArgsConstructor
@Getter
@ToString
@Builder
public class AccountOfUser {

    @PrimaryKey
    private final AccountKey primaryKey;

    private final String currency;

    @Column("balance_in_cents")
    private final Long balanceInCents;

    @PrimaryKeyClass
    @AllArgsConstructor
    @Getter
    @ToString
    @Builder
    public static class AccountKey {
        @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private final UUID userId;

        @PrimaryKeyColumn(name = "account_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private final UUID accountId;
    }
}
