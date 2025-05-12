package App.Clinica.Models;

import java.time.LocalDate;


public class Insurance {
    private String insuranceCompany;
    private String policyNumber;
    private boolean isActive;
    private LocalDate expirationDate;
    private Patient patient;

    public Insurance() {
    }

    public Insurance(String insuranceCompany, String policyNumber, 
                    boolean isActive, LocalDate expirationDate, 
                    Patient patient) {
        this.insuranceCompany = insuranceCompany;
        this.policyNumber = policyNumber;
        this.isActive = isActive;
        this.expirationDate = expirationDate;
        this.patient = patient;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private boolean activo;

    public boolean isActive() {
        return activo;
    }

    
    
} 