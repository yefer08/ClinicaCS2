/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package App.Clinica.Services;

import App.Clinica.Entities.AppointmentEntity;
import App.Clinica.Entities.PatientEntity;
import App.Clinica.Entities.UserEntity;
import App.Clinica.Models.Appointment;
import App.Clinica.Ports.PatientPort;
import App.Clinica.Ports.AppointmentPort;
import App.Clinica.Ports.UserPort;
import App.Clinica.Factories.AppointmentFactory;
import java.time.LocalDate; 
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class AdminService {

    @Autowired
    private PatientPort patientPort;

    @Autowired
    private AppointmentPort appointmentPort;
    
    @Autowired
    private UserPort userPort;

    public PatientEntity registerPatient(PatientEntity newPatient) {
        // Validación de ID único
        if (newPatient.getIdPatient() == null || newPatient.getIdPatient().isBlank()) {
            throw new IllegalArgumentException("El paciente debe tener un número de identificación.");
        }
        if (patientPort.existsById(newPatient.getIdPatient())) {
            throw new RuntimeException("Paciente ya registrado.");
        }

        // Validación de nombre completo
        if (newPatient.getFullName() == null || newPatient.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es requerido.");
        }

        // Validación de fecha de nacimiento
        if (newPatient.getBirthdate() == null || newPatient.getBirthdate().trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento es requerida.");
        }
        try {
            // Convertir el formato DD/MM/YYYY a YYYY-MM-DD
            String[] parts = newPatient.getBirthdate().split("/");
            if (parts.length == 3) {
                String formattedDate = parts[2] + "-" + parts[1] + "-" + parts[0];
                LocalDate birthDate = LocalDate.parse(formattedDate);
                LocalDate now = LocalDate.now();
                if (birthDate.isAfter(now)) {
                    throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
                }
                // Validar que no sea mayor a 150 años
                LocalDate maxAge = now.minusYears(150);
                if (birthDate.isBefore(maxAge)) {
                    throw new IllegalArgumentException("La fecha de nacimiento no puede ser mayor a 150 años.");
                }
                // Actualizar el formato en la entidad
                newPatient.setBirthdate(formattedDate);
            } else {
                throw new IllegalArgumentException("La fecha debe estar en formato DD/MM/YYYY.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha de nacimiento debe tener un formato válido (DD/MM/YYYY).");
        }

        // Validación de género
        if (newPatient.getGender() == null || newPatient.getGender().trim().isEmpty()) {
            throw new IllegalArgumentException("El género es requerido.");
        }
        if (!newPatient.getGender().equalsIgnoreCase("M") && 
            !newPatient.getGender().equalsIgnoreCase("F") && 
            !newPatient.getGender().equalsIgnoreCase("O")) {
            throw new IllegalArgumentException("El género debe ser M (Masculino), F (Femenino) u O (Otro).");
        }

        // Validación de dirección
        if (newPatient.getAddress() == null || newPatient.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es requerida.");
        }
        if (newPatient.getAddress().length() > 200) {
            throw new IllegalArgumentException("La dirección no puede exceder los 200 caracteres.");
        }
        // Validar formato básico de dirección (debe contener al menos un número y una calle)
        String addressPattern = ".*\\d+.*[a-zA-Z]+.*";
        if (!newPatient.getAddress().matches(addressPattern)) {
            throw new IllegalArgumentException("La dirección debe contener al menos un número y una calle.");
        }

        // Validación de teléfono
        if (newPatient.getCellPhone() == null || newPatient.getCellPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono es requerido.");
        }
        String phoneStr = newPatient.getCellPhone();
        if (!phoneStr.matches("^\\d{10}$")) {
            throw new IllegalArgumentException("El número de teléfono debe tener exactamente 10 dígitos.");
        }

        // Validación de email (opcional)
        if (newPatient.getEmail() != null && !newPatient.getEmail().trim().isEmpty()) {
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (!Pattern.matches(emailRegex, newPatient.getEmail())) {
                throw new IllegalArgumentException("El email no tiene un formato válido.");
            }
            if (newPatient.getEmail().length() > 100) {
                throw new IllegalArgumentException("El email no puede exceder los 100 caracteres.");
            }
        }

        return patientPort.save(newPatient);
    }


    public AppointmentEntity scheduleAppointment(Appointment newAppointment) {
        // Validación de fecha de la cita
        if (newAppointment.getDate() == null) {
            throw new IllegalArgumentException("La fecha de la cita es requerida.");
        }
        if (newAppointment.getDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de la cita no puede ser en el pasado.");
        }

        // Validación de motivo
        if (newAppointment.getReason() == null || newAppointment.getReason().trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo de la cita es requerido.");
        }

        AppointmentEntity appointment = AppointmentFactory.create(newAppointment);
        return appointmentPort.save(appointment);
    }
    
    public List<AppointmentEntity> listAppointments() {
        return appointmentPort.findAll();
    }

    public List<PatientEntity> listAllPatients() {
        return patientPort.findAll();
    }

    public PatientEntity getPatientById(String id) {
        return patientPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado."));
    }

    public void deletePatient(String id) {
        if (!patientPort.existsById(id)) {
            throw new RuntimeException("Paciente no existe.");
        }
        patientPort.deleteById(id);
    }

    public UserEntity getUserById(int id) {
        return userPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }
}

