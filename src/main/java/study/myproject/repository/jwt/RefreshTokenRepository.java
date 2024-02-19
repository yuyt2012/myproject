package study.myproject.repository.jwt;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.dto.security.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
}
