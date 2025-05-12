package App.Clinica.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "insurances")
public class InsuranceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insurance_company", nullable = false)
    private String insuranceCompany;

    @Column(name = "policy_number", nullable = false, unique = true)
    private String policyNumber;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private PatientEntity patient;

    public InsuranceEntity() {
    }

    public InsuranceEntity(String insuranceCompany, String policyNumber, 
                         boolean isActive, LocalDate expirationDate, 
                         PatientEntity patient) {
        this.insuranceCompany = insuranceCompany;
        this.policyNumber = policyNumber;
        this.isActive = isActive;
        this.expirationDate = expirationDate;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean b) {
        this.isActive = b;
    }

    
} 