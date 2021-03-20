package io.github.bogdansukonnov.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("accounts")
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Account {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private final String id;

    private final long balance;

    private final String currency;
}
