package App.Clinica.Menus;

import App.Clinica.Services.*;
import App.Clinica.Entities.*;
import App.Clinica.Models.*;
import App.Clinica.converters.PatientConverter;
import App.Clinica.converters.DoctorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class AdministrativeMenu extends BaseMenu {
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private BillingService billingService;
    
    @Autowired
    private InsuranceService insuranceService;
    
    private Scanner scanner = new Scanner(System.in);

    public AdministrativeMenu() {
        super("ADMINISTRATIVE");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== MENU ADMINISTRATIVO ===");
        System.out.println("1. Registrar nuevo paciente");
        System.out.println("2. Registrar nuevo seguro");
        System.out.println("3. Actualizar seguro");
        System.out.println("4. Ver seguros de paciente");
        System.out.println("5. Programar cita");
        System.out.println("6. Generar factura");
        System.out.println("7. Ver citas");
        System.out.println("8. Salir");
    }

    @Override
    public void handleOption(int option) {
        switch (option) {
            case 1:
                handleRegisterPatient();
                break;
            case 2:
                handleRegisterInsurance();
                break;
            case 3:
                handleUpdateInsurance();
                break;
            case 4:
                handleViewPatientInsurances();
                break;
            case 5:
                handleScheduleAppointment();
                break;
            case 6:
                handleGenerateBill();
                break;
            case 7:
                handleViewAppointments();
                break;
            case 8:
                System.out.println("Saliendo del menú administrativo...");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private void handleRegisterPatient() {
        try {
            System.out.println("\n=== Registrar Nuevo Paciente ===");
            PatientEntity newPatient = new PatientEntity();
            
            System.out.print("ID del paciente: ");
            newPatient.setIdPatient(scanner.nextLine());
            
            System.out.print("Nombre completo: ");
            newPatient.setFullName(scanner.nextLine());
            
            System.out.print("Fecha de nacimiento (DD/MM/YYYY): ");
            newPatient.setBirthdate(scanner.nextLine());
            
            System.out.print("Género (M/F/O): ");
            newPatient.setGender(scanner.nextLine());
            
            System.out.print("Dirección: ");
            newPatient.setAddress(scanner.nextLine());
            
            System.out.print("Teléfono: ");
            newPatient.setCellPhone(scanner.nextLine());
            
            System.out.print("Email (opcional): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                newPatient.setEmail(email);
            }
            
            adminService.registerPatient(newPatient);
            System.out.println("Paciente registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error al registrar paciente: " + e.getMessage());
        }
    }

    private void handleRegisterInsurance() {
        try {
            System.out.println("\n=== Registrar Nuevo Seguro ===");
            InsuranceEntity newInsurance = new InsuranceEntity();
            
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            PatientEntity patient = adminService.getPatientById(patientId);
            if (patient == null) {
                throw new IllegalArgumentException("No se encontró el paciente con ID: " + patientId);
            }
            newInsurance.setPatient(patient);
            
            System.out.print("Compañía de seguros: ");
            newInsurance.setInsuranceCompany(scanner.nextLine());
            
            System.out.print("Número de póliza: ");
            newInsurance.setPolicyNumber(scanner.nextLine());
            
            System.out.print("Fecha de expiración (YYYY-MM-DD): ");
            String expirationDateStr = scanner.nextLine();
            newInsurance.setExpirationDate(LocalDate.parse(expirationDateStr));
            
            insuranceService.registerInsurance(newInsurance);
            System.out.println("Seguro registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar seguro: " + e.getMessage());
        }
    }

    private void handleUpdateInsurance() {
        try {
            System.out.println("\n=== Actualizar Seguro ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            InsuranceEntity insurance = insuranceService.getInsuranceByPatientId(patientId);
            
            System.out.print("Nueva compañía de seguros (dejar vacío para mantener actual): ");
            String company = scanner.nextLine();
            if (!company.isEmpty()) {
                insurance.setInsuranceCompany(company);
            }
            
            System.out.print("Nuevo número de póliza (dejar vacío para mantener actual): ");
            String policy = scanner.nextLine();
            if (!policy.isEmpty()) {
                insurance.setPolicyNumber(policy);
            }
            
            System.out.print("Nueva fecha de expiración YYYY-MM-DD (dejar vacío para mantener actual): ");
            String expiration = scanner.nextLine();
            if (!expiration.isEmpty()) {
                insurance.setExpirationDate(LocalDate.parse(expiration));
            }
            
            insuranceService.updateInsurance(insurance);
            System.out.println("Seguro actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar seguro: " + e.getMessage());
        }
    }

    private void handleViewPatientInsurances() {
        try {
            System.out.println("\n=== Ver Seguros de Paciente ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            InsuranceEntity insurance = insuranceService.getInsuranceByPatientId(patientId);
            if (insurance == null) {
                System.out.println("No se encontraron seguros para el paciente.");
                return;
            }
            
            System.out.println("\nSeguros del paciente:");
            System.out.println("\nCompañía: " + insurance.getInsuranceCompany());
            System.out.println("Póliza: " + insurance.getPolicyNumber());
            System.out.println("Fecha de expiración: " + insurance.getExpirationDate());
            System.out.println("Estado: " + (insurance.isActive() ? "Activo" : "Inactivo"));
        } catch (Exception e) {
            System.out.println("Error al ver seguros: " + e.getMessage());
        }
    }

    private void handleScheduleAppointment() {
        try {
            System.out.println("\n=== Programar Cita ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            System.out.print("ID del médico: ");
            int doctorId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Fecha y hora (YYYY-MM-DD HH:MM): ");
            String dateTimeStr = scanner.nextLine();
            
            // Convertir el formato de fecha y hora
            String[] parts = dateTimeStr.split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("El formato de fecha y hora debe ser YYYY-MM-DD HH:MM");
            }
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr.replace(" ", "T") + ":00");
            
            System.out.print("Motivo de la cita: ");
            String reason = scanner.nextLine();
            
            // Get patient and doctor entities
            PatientEntity patient = adminService.getPatientById(patientId);
            UserEntity doctor = adminService.getUserById(doctorId);
            
            // Create appointment model
            Appointment appointment = new Appointment();
            appointment.setIdPatient(PatientConverter.convertToModel(patient));
            appointment.setIdDoctor(DoctorConverter.convertToModel(doctor));
            appointment.setDate(dateTime);
            appointment.setReason(reason);
            
            adminService.scheduleAppointment(appointment);
            System.out.println("Cita programada exitosamente.");
        } catch (DateTimeParseException e) {
            System.out.println("Error al programar cita: El formato de fecha y hora debe ser YYYY-MM-DD HH:MM (ejemplo: 2025-05-30 10:30)");
        } catch (Exception e) {
            System.out.println("Error al programar cita: " + e.getMessage());
        }
    }

    private void handleGenerateBill() {
        try {
            System.out.println("\n=== Generar Factura ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            System.out.print("ID del médico: ");
            int doctorId = Integer.parseInt(scanner.nextLine());
            
            Billing billing = billingService.generateBilling(patientId, doctorId);
            System.out.println("\nFactura generada exitosamente:");
            System.out.println(billing);
        } catch (Exception e) {
            System.out.println("Error al generar factura: " + e.getMessage());
        }
    }

    private void handleViewAppointments() {
        try {
            System.out.println("\n=== Historial de Citas ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            List<AppointmentEntity> appointments = adminService.listAppointments()
                .stream()
                .filter(appointment -> appointment.getIdPatient().getIdPatient().equals(patientId))
                .toList();
            
            if (appointments.isEmpty()) {
                System.out.println("No hay citas registradas para este paciente.");
                return;
            }
            
            for (AppointmentEntity appointment : appointments) {
                System.out.println("\nFecha: " + appointment.getDate());
                System.out.println("Médico: " + appointment.getIdDoctor().getFullName());
                System.out.println("Motivo: " + appointment.getReason());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar citas: " + e.getMessage());
        }
    }

    @Override
    public void display() {
        while (true) {
            displayMenu();
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 8) {
                System.out.println("Saliendo del menú administrativo...");
                return;
            }
            handleOption(option);
        }
    }
} 