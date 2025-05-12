/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import App.Clinica.Entities.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String numeroOrden;
    private PatientEntity patient;
    private UserEntity doctor;
    private LocalDateTime fecha;
    private List<MedicineEntity> medications;
    private List<ProceduresEntity> procedures;
    private List<DiagnosticHelpEntity> diagnosticHelps;

    public Order(String numeroOrden, PatientEntity patient, UserEntity doctor, LocalDateTime fecha) {
        this.numeroOrden = numeroOrden;
        this.patient = patient;
        this.doctor = doctor;
        this.fecha = fecha;
    }
}
