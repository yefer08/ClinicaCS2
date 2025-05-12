package App.Clinica.Menus;

import App.Clinica.Services.*;
import App.Clinica.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

@Component
public class NurseMenu {
    @Autowired
    private NursingRecordService nursingRecordService;
    @Autowired
    private OrderService orderService;
   
    private Scanner scanner = new Scanner(System.in);

    public NurseMenu() {
    }

    public void display() {
        while (true) {
            System.out.println("\n=== MENÚ ENFERMERÍA ===");
            System.out.println("1. Registrar Signos Vitales");
            System.out.println("2. Registrar Intervención de Enfermería");
            System.out.println("3. Ver Registros de Enfermería");
            System.out.println("4. Registrar Visita de Enfermería");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            int option = Integer.parseInt(scanner.nextLine());
            
            if (option == 5) {
                System.out.println("Saliendo del menú de enfermería...");
                return;
            }
            
            handleOption(option);
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1:
                handleVitalSigns();
                break;
            case 2:
                handleNursingIntervention();
                break;
            case 3:
                handleNursingRecords();
                break;
            case 4:
                handleNursingVisit();
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private void handleVitalSigns() {
        try {
            System.out.println("\n=== Registrar Signos Vitales ===");
            System.out.print("Ingrese ID del paciente: ");
            String patientId = scanner.nextLine();
            if (patientId.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del paciente no puede estar vacío");
            }

            NursingRecordEntity record = new NursingRecordEntity();
            PatientEntity patient = new PatientEntity();
            patient.setIdPatient(patientId);
            record.setPatient(patient);
            record.setDateTime(LocalDateTime.now());

            System.out.print("Presión arterial (sistólica/diastólica): ");
            String bloodPressure = scanner.nextLine();
            if (!bloodPressure.matches("\\d+/\\d+")) {
                throw new IllegalArgumentException("Formato de presión arterial inválido. Use el formato sistólica/diastólica (ej: 120/80)");
            }
            record.setBloodPressure(bloodPressure);

            System.out.print("Frecuencia cardíaca: ");
            String heartRateStr = scanner.nextLine();
            try {
                int heartRate = Integer.parseInt(heartRateStr);
                if (heartRate < 0 || heartRate > 300) {
                    throw new IllegalArgumentException("Frecuencia cardíaca fuera de rango (0-300)");
                }
                record.setHeartRate(heartRate);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("La frecuencia cardíaca debe ser un número");
            }

            System.out.print("Temperatura (°C): ");
            String temperatureStr = scanner.nextLine();
            try {
                float temperature = Float.parseFloat(temperatureStr);
                if (temperature < 30 || temperature > 45) {
                    throw new IllegalArgumentException("Temperatura fuera de rango (30-45°C)");
                }
                record.setTemperature(temperature);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("La temperatura debe ser un número");
            }

            System.out.print("Frecuencia respiratoria: ");
            String respiratoryRateStr = scanner.nextLine();
            try {
                int respiratoryRate = Integer.parseInt(respiratoryRateStr);
                if (respiratoryRate < 0 || respiratoryRate > 100) {
                    throw new IllegalArgumentException("Frecuencia respiratoria fuera de rango (0-100)");
                }
                record.setRespiratoryRate(respiratoryRate);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("La frecuencia respiratoria debe ser un número");
            }

            nursingRecordService.createNursingRecord(record);
            System.out.println("Signos vitales registrados exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar signos vitales: " + e.getMessage());
        }
    }

    private void handleNursingIntervention() {
        try {
            System.out.println("\n=== Registrar Intervención de Enfermería ===");
            System.out.print("Ingrese ID del registro de enfermería: ");
            String recordIdStr = scanner.nextLine();
            if (recordIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del registro no puede estar vacío");
            }

            Long recordId;
            try {
                recordId = Long.parseLong(recordIdStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El ID del registro debe ser un número");
            }

            NursingInterventionEntity intervention = new NursingInterventionEntity();
            intervention.setNursingRecordId(recordId);
            intervention.setDateTime(LocalDateTime.now());

            System.out.print("Descripción de la intervención: ");
            String description = scanner.nextLine();
            if (description.trim().isEmpty()) {
                throw new IllegalArgumentException("La descripción no puede estar vacía");
            }
            intervention.setDescription(description);

            System.out.print("Notas adicionales: ");
            String notes = scanner.nextLine();
            intervention.setNotes(notes);

            nursingRecordService.createNursingIntervention(intervention);
            System.out.println("Intervención registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar intervención: " + e.getMessage());
        }
    }

    private void handleNursingRecords() {
        try {
            System.out.println("\n=== Ver Registros de Enfermería ===");
            System.out.print("Ingrese ID del paciente: ");
            String patientId = scanner.nextLine();
            if (patientId.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del paciente no puede estar vacío");
            }

            List<NursingRecordEntity> records = nursingRecordService.getPatientNursingRecords(patientId);
            
            if (records.isEmpty()) {
                System.out.println("No hay registros para este paciente.");
                return;
            }

            for (NursingRecordEntity record : records) {
                System.out.println("\nRegistro ID: " + record.getId());
                System.out.println("Fecha y hora: " + record.getDateTime());
                System.out.println("Presión arterial: " + record.getBloodPressure());
                System.out.println("Frecuencia cardíaca: " + record.getHeartRate());
                System.out.println("Temperatura: " + record.getTemperature() + "°C");
                System.out.println("Frecuencia respiratoria: " + record.getRespiratoryRate());

                List<NursingInterventionEntity> interventions = 
                    nursingRecordService.getRecordInterventions(record.getId());
                if (!interventions.isEmpty()) {
                    System.out.println("\nIntervenciones:");
                    for (NursingInterventionEntity intervention : interventions) {
                        System.out.println("- " + intervention.getDateTime() + ": " + 
                            intervention.getDescription());
                        if (intervention.getNotes() != null && !intervention.getNotes().trim().isEmpty()) {
                            System.out.println("  Notas: " + intervention.getNotes());
                        }
                    }
                }

                List<NursingVisitEntity> visits = 
                    nursingRecordService.getRecordVisits(record.getId());
                if (!visits.isEmpty()) {
                    System.out.println("\nVisitas:");
                    for (NursingVisitEntity visit : visits) {
                        System.out.println("- " + visit.getVisitDate());
                        System.out.println("  Estado: " + visit.getPatientStatus());
                        System.out.println("  Observaciones: " + visit.getObservations());
                    }
                }
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar registros: " + e.getMessage());
        }
    }

    private void handleNursingVisit() {
        try {
            System.out.println("\n=== Registrar Visita de Enfermería ===");
            System.out.print("Ingrese ID del registro de enfermería: ");
            String recordIdStr = scanner.nextLine();
            if (recordIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del registro no puede estar vacío");
            }

            Long recordId;
            try {
                recordId = Long.parseLong(recordIdStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El ID del registro debe ser un número");
            }

            NursingVisitEntity visit = new NursingVisitEntity();
            visit.setOrder(orderService.getPatientOrders(recordId.toString()).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada")));
            visit.setVisitDate(LocalDateTime.now());

            System.out.print("Estado del paciente: ");
            String patientStatus = scanner.nextLine();
            if (patientStatus.trim().isEmpty()) {
                throw new IllegalArgumentException("El estado del paciente no puede estar vacío");
            }
            visit.setPatientStatus(patientStatus);

            System.out.print("Observaciones: ");
            String observations = scanner.nextLine();
            if (observations.trim().isEmpty()) {
                throw new IllegalArgumentException("Las observaciones no pueden estar vacías");
            }
            visit.setObservations(observations);

            nursingRecordService.createNursingVisit(visit, recordId);
            System.out.println("Visita registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar visita: " + e.getMessage());
        }
    }
} 