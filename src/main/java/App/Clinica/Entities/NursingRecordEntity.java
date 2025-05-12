package App.Clinica.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nursing_records")
public class NursingRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id", nullable = false)
    private UserEntity nurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "blood_pressure")
    private String bloodPressure;

    @Column(name = "heart_rate")
    private Integer heartRate;

    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "respiratory_rate")
    private Integer respiratoryRate;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_id")
    private ProceduresEntity procedure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnostic_help_id")
    private DiagnosticHelpEntity diagnosticHelp;

    // Getters and Setters
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

    public UserEntity getNurse() {
        return nurse;
    }

    public void setNurse(UserEntity nurse) {
        this.nurse = nurse;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Integer getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Integer respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MedicineEntity getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineEntity medicine) {
        this.medicine = medicine;
    }

    public ProceduresEntity getProcedure() {
        return procedure;
    }

    public void setProcedure(ProceduresEntity procedure) {
        this.procedure = procedure;
    }

    public DiagnosticHelpEntity getDiagnosticHelp() {
        return diagnosticHelp;
    }

    public void setDiagnosticHelp(DiagnosticHelpEntity diagnosticHelp) {
        this.diagnosticHelp = diagnosticHelp;
    }
} 