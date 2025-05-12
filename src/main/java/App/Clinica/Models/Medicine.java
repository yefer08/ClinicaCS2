/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medicine {
   @GeneratedValue(strategy = GenerationType.UUID)
    private String idMedication;
    private MedicationOrder idMedicationOrder;
    private String dose;
    private int duration;
    private double cost;
    //item, numero que hace referencia dentro de la orden

    public Medicine(String idMedication, MedicationOrder idMedicationOrder, String dose, int duration, double cost) {
        this.idMedication = idMedication;
        this.idMedicationOrder = idMedicationOrder;
        this.dose = dose;
        this.duration = duration;
        this.cost = cost;
    }
}
