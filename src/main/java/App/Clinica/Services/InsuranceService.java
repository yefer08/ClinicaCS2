package App.Clinica.Services;

import App.Clinica.Entities.InsuranceEntity;
import App.Clinica.Ports.InsurancePort;
import App.Clinica.Ports.PatientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Service
public class InsuranceService {

    @Autowired
    private InsurancePort insurancePort;

    @Autowired
    private PatientPort patientPort;

    public InsuranceEntity registerInsurance(InsuranceEntity newInsurance) {
        validateInsurance(newInsurance);
        return insurancePort.save(newInsurance);
    }

    private void validateInsurance(InsuranceEntity insurance) {
        // Validación de compañía de seguros
        if (insurance.getInsuranceCompany() == null || insurance.getInsuranceCompany().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la compañía de seguros es requerido.");
        }
        if (insurance.getInsuranceCompany().length() < 2 || insurance.getInsuranceCompany().length() > 100) {
            throw new IllegalArgumentException("El nombre de la compañía debe tener entre 2 y 100 caracteres.");
        }

        // Validación de número de póliza
        if (insurance.getPolicyNumber() == null || insurance.getPolicyNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de póliza es requerido.");
        }
        if (insurance.getPolicyNumber().length() < 5 || insurance.getPolicyNumber().length() > 20) {
            throw new IllegalArgumentException("El número de póliza debe tener entre 5 y 20 caracteres.");
        }
        if (!Pattern.matches("^[A-Za-z0-9-]+$", insurance.getPolicyNumber())) {
            throw new IllegalArgumentException("El número de póliza solo puede contener letras, números y guiones.");
        }

        // Validación de fecha de expiración
        if (insurance.getExpirationDate() == null) {
            throw new IllegalArgumentException("La fecha de expiración es requerida.");
        }
        if (insurance.getExpirationDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de expiración no puede ser en el pasado.");
        }

        // Validación de paciente
        if (insurance.getPatient() == null) {
            throw new IllegalArgumentException("El paciente es requerido.");
        }
        if (!patientPort.existsById(insurance.getPatient().getIdPatient())) {
            throw new IllegalArgumentException("El paciente no existe en el sistema.");
        }

        // Validación de póliza única por paciente
        if (insurancePort.existsByPatientId(insurance.getPatient().getIdPatient())) {
            throw new IllegalArgumentException("El paciente ya tiene una póliza de seguro registrada.");
        }

        // Actualizar estado de la póliza basado en la fecha de expiración
        insurance.setActive(!insurance.getExpirationDate().isBefore(LocalDate.now()));
    }

    public InsuranceEntity getInsuranceByPatientId(String patientId) {
        return insurancePort.findByPatientId(patientId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró póliza de seguro para el paciente."));
    }

    public void deleteInsurance(Long id) {
        if (!insurancePort.existsById(id)) {
            throw new IllegalArgumentException("La póliza de seguro no existe.");
        }
        insurancePort.deleteById(id);
    }
} 