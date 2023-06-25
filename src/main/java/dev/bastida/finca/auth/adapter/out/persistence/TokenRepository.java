package dev.bastida.finca.auth.adapter.out.persistence;

import dev.bastida.finca.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByValue(String value);

    @Query("""
            select t from Token t inner join Account a\s
            on t.account.id = a.id\s
            where a.id = :id and (t.expired = false or t.revoked = false)
            """)
    List<Token> findAllValidTokensByUser(Long id);
}
