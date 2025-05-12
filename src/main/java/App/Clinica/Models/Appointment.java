/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import java.time.LocalDateTime;


public class Appointment {
    private String idAppointment;
    private Patient idPatient;
    private Doctor idDoctor;
    private LocalDateTime date;
    private String reason;

    public Appointment() {
    }

    public Appointment(String idAppointment, Patient idPatient, Doctor idDoctor, LocalDateTime date, String reason) {
        this.idAppointment = idAppointment;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.date = date;
        this.reason = reason;
    }

    public String getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(String idAppointment) {
        this.idAppointment = idAppointment;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public Doctor getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Doctor idDoctor) {
        this.idDoctor = idDoctor;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    
    
}
