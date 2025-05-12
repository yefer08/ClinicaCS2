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
class Procedures {
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idProcedimiento;
    private OrderProcedure idOrderPrcedure;
    private int cantidad;
    private String frecuencia;
    private double costo;
    private boolean requiereEspecialista;
    private String tipoEspecialidad;
    // Ítem Numero al que hace referencia dentro de la orden

    public Procedures(String idProcedimiento, OrderProcedure idOrderPrcedure, int cantidad, String frecuencia, double costo, boolean requiereEspecialista, String tipoEspecialidad) {
        this.idProcedimiento = idProcedimiento;
        this.idOrderPrcedure = idOrderPrcedure;
        this.cantidad = cantidad;
        this.frecuencia = frecuencia;
        this.costo = costo;
        this.requiereEspecialista = requiereEspecialista;
        this.tipoEspecialidad = tipoEspecialidad;
    }

   
    
    
}
