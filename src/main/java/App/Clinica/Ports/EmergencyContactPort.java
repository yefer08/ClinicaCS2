package App.Clinica.Ports;

import App.Clinica.Entities.EmergencyContactEntity;
import java.util.Optional;

public interface EmergencyContactPort {
    EmergencyContactEntity save(EmergencyContactEntity contact);
    Optional<EmergencyContactEntity> findById(Long id);
    Optional<EmergencyContactEntity> findByPatientId(String patientId);
    boolean existsById(Long id);
    boolean existsByPatientId(String patientId);
    void deleteById(Long id);
} 