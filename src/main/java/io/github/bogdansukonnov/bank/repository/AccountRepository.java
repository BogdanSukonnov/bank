package io.github.bogdansukonnov.bank.repository;

import io.github.bogdansukonnov.bank.model.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CassandraRepository<Account, String> {
}
