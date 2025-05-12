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
public class OrderProcedure extends Order {
    private List<ProceduresEntity> procedures;
    
    public OrderProcedure(String numeroOrden, PatientEntity patient, UserEntity doctor, LocalDateTime fecha) {
        super(numeroOrden, patient, doctor, fecha);
    }
}
