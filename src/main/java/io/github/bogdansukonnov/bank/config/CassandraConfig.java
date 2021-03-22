package io.github.bogdansukonnov.bank.config;

import io.github.bogdansukonnov.bank.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories(basePackages = "io.github.bogdansukonnov.bank.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${cassandra.host}")
    private String host;

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @Override
    public String getContactPoints() {
        return host;
    }

    @Override
    public String getKeyspaceName() {
        return keyspace;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE_DROP_UNUSED;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{User.class.getPackage().getName()};
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification
                .createKeyspace(getKeyspaceName())
                .ifNotExists(true)
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication());
    }
}
