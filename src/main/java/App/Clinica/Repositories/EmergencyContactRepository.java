package App.Clinica.Repositories;

import App.Clinica.Entities.EmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContactEntity, Long> {
    Optional<EmergencyContactEntity> findByPatientIdPatient(String patientId);
    boolean existsByPatientIdPatient(String patientId);
} 