package co.za.mm.repo;

import co.za.mm.model.MSISDNData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MSISDNRepo extends JpaRepository<MSISDNData, Long> {

    Optional<MSISDNData> findByMsisdn(String msisdn);
}
