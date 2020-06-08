package co.za.mm.repo;

import co.za.mm.model.SessionInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionsRepo extends CrudRepository<SessionInformation, Long> {

    Optional<SessionInformation> findBySessionId(String sessionId);

    Optional<SessionInformation> findBySessionIdAndStatusIsNot(String sessionId, String status);

    Optional<SessionInformation> findBySessionIdAndMenu(String sessionId, String menu);
}
