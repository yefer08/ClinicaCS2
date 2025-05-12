package App.Clinica.Ports;

import App.Clinica.Entities.ClinicalHistoryEntity;
import java.util.List;
import java.util.Optional;

public interface ClinicalHistoryPort {
    List<ClinicalHistoryEntity> findByPatientId(String patientId);
    Optional<ClinicalHistoryEntity> findById(String id);
    ClinicalHistoryEntity save(ClinicalHistoryEntity history);
    void deleteById(String id);
    boolean existsById(String id);
} 