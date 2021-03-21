package io.github.bogdansukonnov.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("accounts")
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder=true)
public class Account {

    @Id
    @Column("account_id")
    private final UUID accountId;

    @Column("user_id")
    private final UUID userId;

    private final String currency;

    @Column("balance_in_cents")
    private final Long balanceInCents;
}
