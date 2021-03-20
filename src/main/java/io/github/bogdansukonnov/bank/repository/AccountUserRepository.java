package io.github.bogdansukonnov.bank.repository;

import io.github.bogdansukonnov.bank.model.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountUserRepository extends CassandraRepository<Account, Account.AccountKey> {

    @Query("SELECT * from accounts_by_user where userid = ?0")
    List<Account> getAllAccountsByUserId(String userId);

}
