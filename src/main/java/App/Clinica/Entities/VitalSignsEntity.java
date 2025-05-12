package App.Clinica.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vital_signs")
public class VitalSignsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @Column(nullable = false)
    private LocalDateTime recordDate;

    @Column(name = "blood_pressure")
    private String bloodPressure;

    @Column(nullable = false)
    private double temperature;

    @Column(nullable = false)
    private int pulse;

    @Column(name = "oxygen_level")
    private int oxygenLevel;

    @ManyToOne
    @JoinColumn(name = "nurse_id", nullable = false)
    private UserEntity nurse;

    @Column(length = 500)
    private String observations;

    public VitalSignsEntity(Long id, PatientEntity patient, LocalDateTime recordDate, String bloodPressure, double temperature, int pulse, int oxygenLevel, UserEntity nurse, String observations) {
        this.id = id;
        this.patient = patient;
        this.recordDate = recordDate;
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.pulse = pulse;
        this.oxygenLevel = oxygenLevel;
        this.nurse = nurse;
        this.observations = observations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public void setOxygenLevel(int oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }

    public UserEntity getNurse() {
        return nurse;
    }

    public void setNurse(UserEntity nurse) {
        this.nurse = nurse;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    
} 