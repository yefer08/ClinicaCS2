package App.Clinica.Menus;

import App.Clinica.Services.*;
import App.Clinica.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

@Component
public class DoctorMenu {
    @Autowired
    private ClinicalHistoryService clinicalHistoryService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private ProceduresService proceduresService;
    @Autowired
    private DiagnosticHelpService diagnosticHelpService;

    private Scanner scanner = new Scanner(System.in);

    public DoctorMenu() {
    }

    public void display() {
        while (true) {
            System.out.println("\n=== MENÚ DOCTOR ===");
            System.out.println("1. Registrar Historia Clínica");
            System.out.println("2. Crear Orden de Medicamentos");
            System.out.println("3. Crear Orden de Procedimientos");
            System.out.println("4. Crear Orden de Diagnósticos");
            System.out.println("5. Ver Historias de Pacientes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            int option = Integer.parseInt(scanner.nextLine());
            
            if (option == 6) {
                System.out.println("Saliendo del menú de doctor...");
                return;
            }
            
            handleOption(option);
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1:
                registerClinicalHistory();
                break;
            case 2:
                createMedicationOrder();
                break;
            case 3:
                createProcedureOrder();
                break;
            case 4:
                createDiagnosticOrder();
                break;
            case 5:
                viewPatientHistories();
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private void registerClinicalHistory() {
        try {
            System.out.println("\n=== Registrar Historia Clínica ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            System.out.print("Motivo de consulta: ");
            String consultationReason = scanner.nextLine();
            
            System.out.print("Síntomas: ");
            String symptoms = scanner.nextLine();
            
            System.out.print("Diagnóstico: ");
            String diagnosis = scanner.nextLine();
            
            System.out.print("ID del doctor: ");
            int doctorId = Integer.parseInt(scanner.nextLine());
            
            clinicalHistoryService.createClinicalRecord(
                patientId, doctorId, consultationReason, symptoms, diagnosis
            );
            
            System.out.println("Historia clínica registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar historia clínica: " + e.getMessage());
        }
    }

    private void createMedicationOrder() {
        try {
            System.out.println("\n=== Crear Orden de Medicamentos ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            System.out.print("ID del doctor: ");
            int doctorId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Número de orden: ");
            String numeroOrden = scanner.nextLine();
            
            PatientEntity patient = adminService.getPatientById(patientId);
            UserEntity doctor = new UserEntity();
            doctor.setCedule(doctorId);
            
            // Crear la orden primero
            MedicationOrderEntity order = new MedicationOrderEntity(numeroOrden, patient, doctor, java.time.LocalDateTime.now());
            List<MedicineEntity> medications = new ArrayList<>();
            
            int itemNumber = 1;
            while (true) {
                System.out.print("ID del medicamento (o 'fin' para terminar): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("fin")) break;
                
                try {
                    Integer medicationId = Integer.parseInt(input);
                    
                    System.out.print("Dosis (ej: '1 tableta cada 8 horas'): ");
                    String dose = scanner.nextLine();
                    
                    System.out.print("Duración del tratamiento (días): ");
                    int duration = Integer.parseInt(scanner.nextLine());
                    
                    System.out.print("Costo: ");
                    double cost = Double.parseDouble(scanner.nextLine());
                    
                    // Crear el item de medicamento usando el nuevo método
                    MedicineEntity medicine = orderService.createMedicineItem(
                        order, medicationId, dose, duration, cost, itemNumber++
                    );
                    
                    medications.add(medicine);
                    System.out.println("Medicamento agregado a la orden.");
                } catch (NumberFormatException e) {
                    System.out.println("Por favor ingrese un número válido.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            
            if (!medications.isEmpty()) {
                order.setMedications(medications);
                orderService.createMedicationOrder(numeroOrden, patient, doctor, medications);
                System.out.println("Orden de medicamentos creada exitosamente.");
            } else {
                System.out.println("No se agregaron medicamentos a la orden.");
            }
        } catch (Exception e) {
            System.out.println("Error al crear orden de medicamentos: " + e.getMessage());
        }
    }

    private void createProcedureOrder() {
        try {
            System.out.println("\n=== Crear Orden de Procedimientos ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            System.out.print("ID del doctor: ");
            int doctorId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Número de orden: ");
            String numeroOrden = scanner.nextLine();
            
            List<ProceduresEntity> procedures = new ArrayList<>();
            while (true) {
                System.out.print("ID del procedimiento (o 'fin' para terminar): ");
                String procId = scanner.nextLine();
                if (procId.equalsIgnoreCase("fin")) break;
                
                ProceduresEntity procedure = proceduresService.getProcedureById(procId);
                if (procedure != null) {
                    procedures.add(procedure);
                } else {
                    System.out.println("Procedimiento no encontrado");
                }
            }
            
            PatientEntity patient = adminService.getPatientById(patientId);
            UserEntity doctor = new UserEntity();
            doctor.setCedule(doctorId);
            
            orderService.createProcedureOrder(numeroOrden, patient, doctor, procedures);
            System.out.println("Orden de procedimientos creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear orden de procedimientos: " + e.getMessage());
        }
    }

    private void createDiagnosticOrder() {
        try {
            System.out.println("\n=== Crear Orden de Diagnósticos ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            System.out.print("ID del doctor: ");
            int doctorId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Número de orden: ");
            String numeroOrden = scanner.nextLine();
            
            List<DiagnosticHelpEntity> exams = new ArrayList<>();
            while (true) {
                System.out.print("ID del examen (o 'fin' para terminar): ");
                String examId = scanner.nextLine();
                if (examId.equalsIgnoreCase("fin")) break;
                
                DiagnosticHelpEntity exam = diagnosticHelpService.getDiagnosticHelpById(examId);
                if (exam != null) {
                    exams.add(exam);
                } else {
                    System.out.println("Examen no encontrado");
                }
            }
            
            PatientEntity patient = adminService.getPatientById(patientId);
            UserEntity doctor = new UserEntity();
            doctor.setCedule(doctorId);
            
            orderService.createDiagnosticOrder(numeroOrden, patient, doctor, exams);
            System.out.println("Orden de diagnósticos creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear orden de diagnósticos: " + e.getMessage());
        }
    }

    private void viewPatientHistories() {
        try {
            System.out.println("\n=== Ver Historias de Pacientes ===");
            System.out.print("ID del paciente: ");
            String patientId = scanner.nextLine();
            
            List<ClinicalHistoryEntity> histories = clinicalHistoryService.getPatientHistory(patientId);
            
            if (histories.isEmpty()) {
                System.out.println("No hay historias clínicas registradas para este paciente.");
                return;
            }
            
            for (ClinicalHistoryEntity history : histories) {
                System.out.println("\nFecha: " + history.getDate());
                System.out.println("Médico: " + history.getDoctor().getFullName());
                System.out.println("Motivo de consulta: " + history.getConsultationReason());
                System.out.println("Síntomas: " + history.getSymptoms());
                System.out.println("Diagnóstico: " + history.getDiagnosis());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar historias: " + e.getMessage());
        }
    }
} 