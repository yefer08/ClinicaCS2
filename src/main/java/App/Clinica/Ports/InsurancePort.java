package App.Clinica.Ports;

import App.Clinica.Entities.InsuranceEntity;
import java.util.Optional;

public interface InsurancePort {
    InsuranceEntity save(InsuranceEntity insurance);
    Optional<InsuranceEntity> findById(Long id);
    Optional<InsuranceEntity> findByPatientId(String patientId);
    boolean existsById(Long id);
    boolean existsByPatientId(String patientId);
    void deleteById(Long id);
} 