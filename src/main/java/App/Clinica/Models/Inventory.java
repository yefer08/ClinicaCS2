/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {
    private List<String> medications;
    private List<String> procedures;
    private List<String> diagnosticHelp;
    private List<String> specialties;

    public Inventory() {
    }

    public Inventory(List<String> medications, List<String> procedures, 
                     List<String> diagnosticHelp, List<String> specialties) {
        this.medications = medications;
        this.procedures = procedures;
        this.diagnosticHelp = diagnosticHelp;
        this.specialties = specialties;
    }
}
