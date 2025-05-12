/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Factories;

import App.Clinica.Entities.PatientEntity;
import App.Clinica.Models.Patient;

public class PatientFactory {

    public static PatientEntity create(Patient patient) {
        return new PatientEntity(
                patient.getIdPatient(),
                patient.getFullName(),
                patient.getBirthdate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getCellPhone(),
                patient.getEmail()
        );
    }
}
