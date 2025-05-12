package App.Clinica.Services;

import App.Clinica.Entities.*;
import App.Clinica.Ports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NursingService {
    @Autowired
    private VitalSignsPort vitalSignsPort;

    @Autowired
    private NursingRecordPort nursingRecordPort;

    @Autowired
    private PatientPort patientPort;

    @Autowired
    private UserPort userPort;

    @Autowired
    private OrderPort orderPort;

    public VitalSignsEntity registerVitalSigns(VitalSignsEntity vitalSigns) {
        validateVitalSigns(vitalSigns);
        vitalSigns.setRecordDate(LocalDateTime.now());
        return vitalSignsPort.save(vitalSigns);
    }

    public NursingRecordEntity registerMedicationAdministration(NursingRecordEntity record) {
        validateNursingRecord(record);
        record.setDateTime(LocalDateTime.now());
        record.setStatus("COMPLETED");
        return nursingRecordPort.save(record);
    }

    public List<VitalSignsEntity> getPatientVitalSigns(String patientId) {
        return vitalSignsPort.findByPatientIdPatient(patientId);
    }

    public List<NursingRecordEntity> getPatientNursingRecords(String patientId) {
        return nursingRecordPort.findByPatientIdPatient(patientId);
    }

    private void validateVitalSigns(VitalSignsEntity vitalSigns) {
        if (vitalSigns.getPatient() == null || !patientPort.existsById(vitalSigns.getPatient().getIdPatient())) {
            throw new IllegalArgumentException("Paciente no válido");
        }

        if (vitalSigns.getNurse() == null || !userPort.existsById(vitalSigns.getNurse().getCedule())) {
            throw new IllegalArgumentException("Enfermera no válida");
        }

        if (vitalSigns.getTemperature() < 35.0 || vitalSigns.getTemperature() > 42.0) {
            throw new IllegalArgumentException("Temperatura fuera de rango válido (35-42°C)");
        }

        if (vitalSigns.getPulse() < 40 || vitalSigns.getPulse() > 200) {
            throw new IllegalArgumentException("Pulso fuera de rango válido (40-200)");
        }

        if (vitalSigns.getOxygenLevel() < 0 || vitalSigns.getOxygenLevel() > 100) {
            throw new IllegalArgumentException("Nivel de oxígeno fuera de rango válido (0-100%)");
        }
    }

    private void validateNursingRecord(NursingRecordEntity record) {
        if (record.getPatient() == null || !patientPort.existsById(record.getPatient().getIdPatient())) {
            throw new IllegalArgumentException("Paciente no válido");
        }

        if (record.getNurse() == null || !userPort.existsById(record.getNurse().getCedule())) {
            throw new IllegalArgumentException("Enfermera no válida");
        }

        if (record.getOrder() == null || !orderPort.existsById(record.getOrder().getNumeroOrden())) {
            throw new IllegalArgumentException("Orden no válida");
        }

        // Validar que al menos un tipo de ítem esté presente
        if (record.getMedicine() == null && record.getProcedure() == null && record.getDiagnosticHelp() == null) {
            throw new IllegalArgumentException("Debe especificar al menos un medicamento, procedimiento o ayuda diagnóstica");
        }
    }
} 