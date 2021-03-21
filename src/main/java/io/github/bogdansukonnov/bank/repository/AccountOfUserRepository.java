package io.github.bogdansukonnov.bank.repository;

import io.github.bogdansukonnov.bank.model.AccountOfUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountOfUserRepository extends CassandraRepository<AccountOfUser, AccountOfUser.AccountKey> {

    @Query("SELECT * from accounts_by_user where user_id = ?0")
    List<AccountOfUser> getAllAccountsByUserId(UUID userId);

}
