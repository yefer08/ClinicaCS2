package App.Clinica.Services;

import App.Clinica.Entities.*;
import App.Clinica.Models.Role;
import App.Clinica.Ports.ClinicalHistoryPort;
import App.Clinica.Ports.PatientPort;
import App.Clinica.Ports.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClinicalHistoryService {

    @Autowired
    private ClinicalHistoryPort clinicalHistoryPort;

    @Autowired
    private PatientPort patientPort;

    @Autowired
    private UserPort userPort;

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

    public List<ClinicalHistoryEntity> getPatientHistory(String patientId) {
        return clinicalHistoryPort.findByPatientId(patientId);
    }

    public ClinicalHistoryEntity getRecordById(String id) {
        return clinicalHistoryPort.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
    }

    public void deleteRecord(String id) {
        if (!clinicalHistoryPort.existsById(id)) {
            throw new IllegalArgumentException("Registro no encontrado");
        }
        clinicalHistoryPort.deleteById(id);
    }
} 