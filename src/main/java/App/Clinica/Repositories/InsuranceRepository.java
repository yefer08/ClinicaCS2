package App.Clinica.Repositories;

import App.Clinica.Entities.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {
    Optional<InsuranceEntity> findByPatientIdPatient(String patientId);
    boolean existsByPatientIdPatient(String patientId);
} 