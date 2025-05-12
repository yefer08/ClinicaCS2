package App.Clinica.Repositories;

import App.Clinica.Entities.NursingRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for nursing record operations.
 * Provides methods for accessing and managing nursing record data in the database.
 */
@Repository
public interface NursingRecordRepository extends JpaRepository<NursingRecordEntity, Long> {
    
    /**
     * Finds all nursing records associated with a specific patient.
     *
     * @param patientId the ID of the patient
     * @return a list of nursing records for the specified patient
     */
    List<NursingRecordEntity> findByPatientIdPatient(String patientId);
} 