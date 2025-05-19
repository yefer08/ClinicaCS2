package App.Clinica.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medicine_entity")
public class MedicineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idMedication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_order_id", nullable = false)
    private MedicationOrderEntity medicationOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id", nullable = false)
    private MedicationEntity medication;

    @Column(nullable = false)
    private String dose;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private int itemNumber;

    public MedicineEntity() {}

    public MedicineEntity(MedicationOrderEntity medicationOrder, MedicationEntity medication, 
                         String dose, int duration, double cost, int itemNumber) {
        this.medicationOrder = medicationOrder;
        this.medication = medication;
        this.dose = dose;
        this.duration = duration;
        this.cost = cost;
        this.itemNumber = itemNumber;
    }

    public String getIdMedication() {
        return idMedication;
    }

    public void setIdMedication(String idMedication) {
        this.idMedication = idMedication;
    }

    public MedicationOrderEntity getMedicationOrder() {
        return medicationOrder;
    }

    public void setMedicationOrder(MedicationOrderEntity medicationOrder) {
        this.medicationOrder = medicationOrder;
    }

    public MedicationEntity getMedication() {
        return medication;
    }

    public void setMedication(MedicationEntity medication) {
        this.medication = medication;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }
    
    
}
