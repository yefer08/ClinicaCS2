package App.Clinica.Repositories;

import App.Clinica.Entities.ClinicalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistoryEntity, String> {
    List<ClinicalHistoryEntity> findByPatientIdPatient(String patientId);
} 