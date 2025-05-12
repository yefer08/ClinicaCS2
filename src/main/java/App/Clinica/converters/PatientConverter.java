/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.converters;

import App.Clinica.Entities.PatientEntity;
import App.Clinica.Models.Patient;

/**
 *
 * @author User
 */
public class PatientConverter {
    public static PatientEntity convertToEntity(Patient model) {
        if (model == null) return null;
        return new PatientEntity(
            model.getIdPatient(),
            model.getFullName(),
            model.getBirthdate(),
            model.getGender(),
            model.getAddress(),
            model.getCellPhone(),
            model.getEmail()
        );
    }

    public static Patient convertToModel(PatientEntity entity) {
        if (entity == null) return null;
        return new Patient(
            entity.getIdPatient(),
            entity.getFullName(),
            entity.getBirthdate(),
            entity.getGender(),
            entity.getAddress(),
            entity.getCellPhone(),
            entity.getEmail()
        );
    }
}
