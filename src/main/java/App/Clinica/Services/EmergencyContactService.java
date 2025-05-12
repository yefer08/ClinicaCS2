package App.Clinica.Services;

import App.Clinica.Entities.EmergencyContactEntity;
import App.Clinica.Ports.EmergencyContactPort;
import App.Clinica.Ports.PatientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmergencyContactService {

    @Autowired
    private EmergencyContactPort emergencyContactPort;

    @Autowired
    private PatientPort patientPort;

    public EmergencyContactEntity registerEmergencyContact(EmergencyContactEntity newContact) {
        validateContact(newContact);
        return emergencyContactPort.save(newContact);
    }

    private void validateContact(EmergencyContactEntity contact) {
        // Validación de nombre
        if (contact.getFirstName() == null || contact.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del contacto de emergencia es requerido.");
        }
        if (contact.getFirstName().length() < 2 || contact.getFirstName().length() > 50) {
            throw new IllegalArgumentException("El nombre debe tener entre 2 y 50 caracteres.");
        }

        // Validación de apellido
        if (contact.getLastName() == null || contact.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del contacto de emergencia es requerido.");
        }
        if (contact.getLastName().length() < 2 || contact.getLastName().length() > 50) {
            throw new IllegalArgumentException("El apellido debe tener entre 2 y 50 caracteres.");
        }

        // Validación de relación
        if (contact.getRelationship() == null || contact.getRelationship().trim().isEmpty()) {
            throw new IllegalArgumentException("La relación con el paciente es requerida.");
        }
        if (contact.getRelationship().length() < 2 || contact.getRelationship().length() > 50) {
            throw new IllegalArgumentException("La relación debe tener entre 2 y 50 caracteres.");
        }

        // Validación de paciente
        if (contact.getPatient() == null) {
            throw new IllegalArgumentException("El paciente es requerido.");
        }
        if (!patientPort.existsById(contact.getPatient().getIdPatient())) {
            throw new IllegalArgumentException("El paciente no existe en el sistema.");
        }

        // Validación de número de teléfono
        if (contact.getPhoneNumber() == null || contact.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono es requerido.");
        }
        String phonePattern = "^\\d{10}$";
        if (!Pattern.matches(phonePattern, contact.getPhoneNumber())) {
            throw new IllegalArgumentException("El número de teléfono debe tener exactamente 10 dígitos.");
        }

        // Validación de contacto único por paciente
        if (emergencyContactPort.existsByPatientId(contact.getPatient().getIdPatient())) {
            throw new IllegalArgumentException("El paciente ya tiene un contacto de emergencia registrado.");
        }
    }

    public EmergencyContactEntity getEmergencyContactByPatientId(String patientId) {
        return emergencyContactPort.findByPatientId(patientId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró contacto de emergencia para el paciente."));
    }

    public void deleteEmergencyContact(Long id) {
        if (!emergencyContactPort.existsById(id)) {
            throw new IllegalArgumentException("El contacto de emergencia no existe.");
        }
        emergencyContactPort.deleteById(id);
    }
} 