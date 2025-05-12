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
public class DiagnosticOrder extends Order {
    private List<DiagnosticHelpEntity> exams;

    public DiagnosticOrder(String numeroOrden, PatientEntity patient, UserEntity doctor, LocalDateTime fecha) {
        super(numeroOrden, patient, doctor, fecha);
    }
}
