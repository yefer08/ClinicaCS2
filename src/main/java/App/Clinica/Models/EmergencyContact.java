/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmergencyContact {
    private String name;
    private String lastName;
    private Patient relaPatient;
    private int cellPhone;

    public EmergencyContact(String name, String lastName, Patient relaPatient, int cellPhone) {
        this.name = name;
        this.lastName = lastName;
        this.relaPatient = relaPatient;
        this.cellPhone = cellPhone;
    }

    
    
    
}
