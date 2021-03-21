package io.github.bogdansukonnov.bank.repository;

import io.github.bogdansukonnov.bank.model.Operation;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CassandraRepository<Operation, Operation.OperationKey> {
}
