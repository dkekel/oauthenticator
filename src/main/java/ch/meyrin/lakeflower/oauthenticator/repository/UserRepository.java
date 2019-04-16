package ch.meyrin.lakeflower.oauthenticator.repository;

import ch.meyrin.lakeflower.oauthenticator.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsername(String username);
}
