package io.github.bogdansukonnov.bank.repository;

import io.github.bogdansukonnov.bank.model.OperationByAccount;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationByAccountRepository extends CassandraRepository<OperationByAccount, String> {
}
