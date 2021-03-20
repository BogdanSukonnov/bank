package io.github.bogdansukonnov.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("users")
@AllArgsConstructor
@Getter
@ToString
@Builder
public class User {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private final String id;

    @Column("first_name")
    private final String firstName;

    @Column("last_name")
    private final String lastName;
}
