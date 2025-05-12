/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Factories;

import App.Clinica.Entities.AppointmentEntity;
import App.Clinica.Models.Appointment;
import App.Clinica.converters.PatientConverter;
import App.Clinica.converters.UserConverter;
import java.util.UUID;

public class AppointmentFactory {

    public static AppointmentEntity create(Appointment appointment) {
        return new AppointmentEntity(
                UUID.randomUUID().toString(),
                PatientConverter.convertToEntity(appointment.getIdPatient()),
                UserConverter.convertToEntity(appointment.getIdDoctor()),
                appointment.getDate(),
                appointment.getReason()
        );
    }
}
