package App.Clinica.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name = "clinical_histories")
public class ClinicalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private UserEntity doctor;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false, length = 500)
    private String consultationReason;

    @Column(nullable = false, length = 1000)
    private String symptoms;

    @Column(nullable = false, length = 1000)
    private String diagnosis;

    @Column(nullable = false)
    private int doctorCedule;

    public ClinicalHistoryEntity() {}

    public ClinicalHistoryEntity(PatientEntity patient, UserEntity doctor, String consultationReason, 
                               String symptoms, String diagnosis) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = LocalDateTime.now();
        this.consultationReason = consultationReason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.doctorCedule = doctor.getCedule();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getConsultationReason() {
        return consultationReason;
    }

    public void setConsultationReason(String consultationReason) {
        this.consultationReason = consultationReason;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getDoctorCedule() {
        return doctorCedule;
    }

    public void setDoctorCedule(int doctorCedule) {
        this.doctorCedule = doctorCedule;
    }
    
    
} 