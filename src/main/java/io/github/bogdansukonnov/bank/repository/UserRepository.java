package io.github.bogdansukonnov.bank.repository;

import io.github.bogdansukonnov.bank.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {
}
