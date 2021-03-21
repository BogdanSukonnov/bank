package io.github.bogdansukonnov.bank.repository;

import io.github.bogdansukonnov.bank.model.Operation;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OperationRepository extends CassandraRepository<Operation, Operation.OperationKey> {

    @Query("SELECT * from operations_by_account where account_id = ?0")
    List<Operation> getAllOperationsByAccountId(UUID accountId);
}
