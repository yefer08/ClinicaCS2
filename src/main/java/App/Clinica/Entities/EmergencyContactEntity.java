package App.Clinica.Entities;

import jakarta.persistence.*;


@Entity
@Table(name = "emergency_contacts")
public class EmergencyContactEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "relationship", nullable = false)
    private String relationship;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private PatientEntity patient;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public EmergencyContactEntity() {
    }

    public EmergencyContactEntity(String firstName, String lastName, String relationship, 
                                PatientEntity patient, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.patient = patient;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
} 