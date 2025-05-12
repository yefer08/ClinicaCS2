/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import App.Clinica.Entities.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
public class Billing {
    private Patient patient;
    private User doctor;
    private Insurance insurance;
    private int patientAge;
    private int remainingDays;
    private List<OrderEntity> orders;
    private double totalCost;
    private double patientCopay;
    private double insuranceCoverage;
    private static final double STANDARD_COPAY = 50000;
    private static final double MAX_YEARLY_COPAY = 1000000;

    public Billing(Patient patient, User doctor, Insurance insurance) {
        this.patient = patient;
        this.doctor = doctor;
        this.insurance = insurance;
        this.patientAge = calculateAge(patient.getBirthdate());
        this.remainingDays = calculateRemainingDays(insurance.getExpirationDate());
        this.orders = new ArrayList<>();
        this.totalCost = 0;
        this.patientCopay = 0;
        this.insuranceCoverage = 0;
    }

    private int calculateAge(String birthdate) {
        LocalDate birth = LocalDate.parse(birthdate);
        return Period.between(birth, LocalDate.now()).getYears();
    }

    private int calculateRemainingDays(LocalDate expirationDate) {
        return Period.between(LocalDate.now(), expirationDate).getDays();
    }

    public void addOrder(OrderEntity order) {
        orders.add(order);
        totalCost += calculateOrderTotal(order);
    }

    private double calculateOrderTotal(OrderEntity order) {
        double total = 0;
        
        if (order instanceof MedicationOrderEntity medOrder) {
            total += medOrder.getMedications().stream()
                .mapToDouble(MedicineEntity::getCost)
                .sum();
        }
        
        if (order instanceof OrderProcedureEntity procOrder) {
            total += procOrder.getProcedures().stream()
                .mapToDouble(ProceduresEntity::getCost)
                .sum();
        }
        
        if (order instanceof DiagnosticOrderEntity diagOrder) {
            total += diagOrder.getExams().stream()
                .mapToDouble(DiagnosticHelpEntity::getCost)
                .sum();
        }
        
        return total;
    }

    public void calculatePayment() {
        if (!insurance.isActive()) {
            patientCopay = totalCost;
            insuranceCoverage = 0;
            return;
        }

        if (getYearlyCopay() >= MAX_YEARLY_COPAY) {
            patientCopay = 0;
            insuranceCoverage = totalCost;
            return;
        }

        patientCopay = Math.min(STANDARD_COPAY, totalCost);
        insuranceCoverage = totalCost - patientCopay;
    }

    private double getYearlyCopay() {
        return 0; // TODO: Implement yearly copay calculation
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Información de Facturación:\n")
          .append("Paciente: ").append(patient.getFullName()).append("\n")
          .append("Edad: ").append(patientAge).append(" años\n")
          .append("Cédula: ").append(patient.getIdPatient()).append("\n")
          .append("Médico: ").append(doctor.getFullName()).append("\n")
          .append("Compañía de Seguros: ").append(insurance.getInsuranceCompany()).append("\n")
          .append("Número de Póliza: ").append(insurance.getPolicyNumber()).append("\n")
          .append("Estado de Póliza: ").append(insurance.isActive() ? "Activa" : "Inactiva").append("\n")
          .append("Días Restantes: ").append(remainingDays).append("\n")
          .append("Fecha de Expiración: ").append(insurance.getExpirationDate()).append("\n\n");

        for (OrderEntity order : orders) {
            sb.append("Orden #").append(order.getNumeroOrden()).append(":\n");
            
            if (order instanceof MedicationOrderEntity medOrder) {
                sb.append("  Medicamentos:\n");
                for (MedicineEntity med : medOrder.getMedications()) {
                    sb.append("  - ID: ").append(med.getIdMedication())
                      .append(" (Dosis: ").append(med.getDose())
                      .append(", Duración: ").append(med.getDuration())
                      .append(", Costo: $").append(med.getCost()).append(")\n");
                }
            }

            if (order instanceof OrderProcedureEntity procOrder) {
                sb.append("  Procedimientos:\n");
                for (ProceduresEntity proc : procOrder.getProcedures()) {
                    sb.append("  - ID: ").append(proc.getIdProcedimiento())
                      .append(" (Cantidad: ").append(proc.getAmount())
                      .append(", Frecuencia: ").append(proc.getFrequency())
                      .append(", Costo: $").append(proc.getCost()).append(")\n");
                }
            }

            if (order instanceof DiagnosticOrderEntity diagOrder) {
                sb.append("  Ayudas Diagnósticas:\n");
                for (DiagnosticHelpEntity help : diagOrder.getExams()) {
                    sb.append("  - ID: ").append(help.getIdDiagnostico())
                      .append(" (Cantidad: ").append(help.getAmount())
                      .append(", Costo: $").append(help.getCost()).append(")\n");
                }
            }
            sb.append("  Total Orden: $").append(calculateOrderTotal(order)).append("\n\n");
        }

        sb.append("Resumen de Costos:\n")
          .append("Costo Total: $").append(totalCost).append("\n")
          .append("Copago Paciente: $").append(patientCopay).append("\n")
          .append("Cobertura Seguro: $").append(insuranceCoverage).append("\n");

        return sb.toString();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getPatientCopay() {
        return patientCopay;
    }

    public void setPatientCopay(double patientCopay) {
        this.patientCopay = patientCopay;
    }

    public double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(double insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }
    
    
}
