package App.Clinica.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type")
@Table(name = "orders")
public abstract class OrderEntity {
    
    @Id
    @Column(name = "numero_orden", length = 6)
    private String numeroOrden;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private UserEntity doctor;
    
    @Column(nullable = false)
    private LocalDateTime fecha;
    
    @Column(nullable = false)
    private boolean requiresHospitalization;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NursingVisitEntity> nursingVisits;
    
    @Transient
    private Set<Integer> usedItemNumbers;
    
    public OrderEntity() {
        this.usedItemNumbers = new HashSet<>();
        this.nursingVisits = new HashSet<>();
    }
    
    public OrderEntity(String numeroOrden, PatientEntity patient, UserEntity doctor, LocalDateTime fecha) {
        this();
        this.numeroOrden = numeroOrden;
        this.patient = patient;
        this.doctor = doctor;
        this.fecha = fecha;
    }
    
    public void validateItemNumber(int itemNumber) {
        if (itemNumber < 1) {
            throw new IllegalArgumentException("El número de ítem debe ser mayor o igual a 1");
        }
        if (usedItemNumbers.contains(itemNumber)) {
            throw new IllegalArgumentException("El número de ítem " + itemNumber + " ya está en uso en esta orden");
        }
        usedItemNumbers.add(itemNumber);
    }
    
    public void addNursingVisit(NursingVisitEntity visit) {
        if (visit == null) {
            throw new IllegalArgumentException("La visita de enfermería no puede ser nula");
        }
        visit.setOrder(this);
        nursingVisits.add(visit);
    }
    
    public void setRequiresHospitalization(boolean requiresHospitalization) {
        this.requiresHospitalization = requiresHospitalization;
        if (requiresHospitalization) {
            // Add default nursing visits for hospitalization
            addDefaultNursingVisits();
        }
    }
    
    private void addDefaultNursingVisits() {
        // Add morning visit
        NursingVisitEntity morningVisit = new NursingVisitEntity();
        morningVisit.setVisitType("MORNING");
        morningVisit.setDescription("Visita matutina de control");
        addNursingVisit(morningVisit);
        
        // Add afternoon visit
        NursingVisitEntity afternoonVisit = new NursingVisitEntity();
        afternoonVisit.setVisitType("AFTERNOON");
        afternoonVisit.setDescription("Visita vespertina de control");
        addNursingVisit(afternoonVisit);
        
        // Add night visit
        NursingVisitEntity nightVisit = new NursingVisitEntity();
        nightVisit.setVisitType("NIGHT");
        nightVisit.setDescription("Visita nocturna de control");
        addNursingVisit(nightVisit);
    }

    public boolean isDiagnosticOrder() {
        return this instanceof DiagnosticOrderEntity;
    }

    public boolean isMedicationOrder() {
        return this instanceof MedicationOrderEntity;
    }

    public boolean isProcedureOrder() {
        return this instanceof OrderProcedureEntity;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Set<NursingVisitEntity> getNursingVisits() {
        return nursingVisits;
    }

    public void setNursingVisits(Set<NursingVisitEntity> nursingVisits) {
        this.nursingVisits = nursingVisits;
    }

    public Set<Integer> getUsedItemNumbers() {
        return usedItemNumbers;
    }

    public void setUsedItemNumbers(Set<Integer> usedItemNumbers) {
        this.usedItemNumbers = usedItemNumbers;
    }

    public boolean isRequiresHospitalization() {
        return requiresHospitalization;
    }
    
    
}


