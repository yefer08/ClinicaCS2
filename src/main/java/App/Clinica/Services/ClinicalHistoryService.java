package App.Clinica.Services;

import App.Clinica.Entities.*;
import App.Clinica.Models.Role;
import App.Clinica.Ports.ClinicalHistoryPort;
import App.Clinica.Ports.PatientPort;
import App.Clinica.Ports.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Service
public class ClinicalHistoryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClinicalHistoryPort clinicalHistoryPort;

    @Autowired
    private PatientPort patientPort;

    @Autowired
    private UserPort userPort;

    @Transactional
    public ClinicalHistoryEntity createClinicalRecord(String patientId, int doctorId, 
                                                    String consultationReason, 
                                                    String symptoms, String diagnosis) {
        // Validate patient exists
        PatientEntity patient = patientPort.findById(patientId)
            .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));

        // Validate doctor exists and is a doctor
        UserEntity doctor = userPort.findById(doctorId)
            .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"));
        
        if (doctor.getRole() != Role.DOCTOR) {
            throw new IllegalArgumentException("El usuario no es un médico");
        }

        // Create and save clinical history record
        ClinicalHistoryEntity record = new ClinicalHistoryEntity(
            patient, doctor, consultationReason, symptoms, diagnosis
        );

        return clinicalHistoryPort.save(record);
    }

    @Transactional(readOnly = true)
    public List<ClinicalHistoryEntity> getPatientHistory(String patientId) {
        List<ClinicalHistoryEntity> histories = clinicalHistoryPort.findByPatientId(patientId);
        
        // Initialize lazy relationships
        for (ClinicalHistoryEntity history : histories) {
            entityManager.refresh(history);
            // Force initialization of the doctor entity
            history.getDoctor().getFullName(); // This will force the proxy to load
        }
        
        return histories;
    }

    @Transactional(readOnly = true)
    public ClinicalHistoryEntity getRecordById(String id) {
        ClinicalHistoryEntity record = clinicalHistoryPort.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        entityManager.refresh(record);
        return record;
    }

    @Transactional
    public void deleteRecord(String id) {
        if (!clinicalHistoryPort.existsById(id)) {
            throw new IllegalArgumentException("Registro no encontrado");
        }
        clinicalHistoryPort.deleteById(id);
    }
} 