package io.github.bogdansukonnov.bank.repository;

import io.github.bogdansukonnov.bank.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
}
