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
class DiagnosticHelp  {
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ihelpudaDiagnostica;
    private DiagnosticOrder idOrderDiagnostica;
    private int amount;
    private boolean requiresSpecialist;
    private String typeSpecialty; // se relaciona con a partir de un inventario. 
    // Ítem Numero al que hace referencia dentro de la orden

    public DiagnosticHelp(String ihelpudaDiagnostica, DiagnosticOrder idOrderDiagnostica, int amount, double costo, boolean requiresSpecialist, String typeSpecialty) {
        this.ihelpudaDiagnostica = ihelpudaDiagnostica;
        this.idOrderDiagnostica = idOrderDiagnostica;
        this.amount = amount;
        this.requiresSpecialist = false;
        this.typeSpecialty = typeSpecialty;
    }
}
