package io.github.bogdansukonnov.bank.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
        basePackages = "io.github.bogdansukonnov.bank.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${cassandra.host}")
    private String host;

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @Bean(name = "cassandraMigrationCqlSession")
    public CqlSession cassandraMigrationCqlSession() {
        return CqlSession.builder().withKeyspace(getKeyspaceName()).build();
    }

    @Bean
    @Primary
    public CqlSession applicationCqlSession() {
        return CqlSession.builder().withKeyspace(getKeyspaceName()).build();
    }

    public String getContactPoints() {
        return host;
    }

    public String getKeyspaceName() {
        return keyspace;
    }
}
