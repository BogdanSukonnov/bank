package io.github.bogdansukonnov.bank;

import com.datastax.oss.driver.api.core.CqlSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.cognitor.cassandra.migration.Database;
import org.cognitor.cassandra.migration.MigrationRepository;
import org.cognitor.cassandra.migration.MigrationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class CassandraMigrationRunner implements CommandLineRunner {

    @NonNull
    private CqlSession cqlSession;

    @NonNull
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting DB Migration");
        Database database = new Database(cqlSession, environment.getProperty("cassandra.keyspace"));
        MigrationTask migration = new MigrationTask(database, new MigrationRepository("resources/cassandra/migration"));
        migration.migrate();
        log.info("DB Migration Complete");
    }
}
