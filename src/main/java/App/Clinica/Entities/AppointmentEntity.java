/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;





@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idAppointment;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity idPatient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private UserEntity idDoctor;

    private LocalDateTime date;

    private String reason;

    public AppointmentEntity() {
    }

    public AppointmentEntity(String idAppointment, PatientEntity idPatient, UserEntity idDoctor, LocalDateTime date, String reason) {
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

    public PatientEntity getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(PatientEntity idPatient) {
        this.idPatient = idPatient;
    }

    public UserEntity getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(UserEntity idDoctor) {
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