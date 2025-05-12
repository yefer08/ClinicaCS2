package App.Clinica.Entities;

import java.util.List;
import java.time.LocalDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("MEDICATION")
public class MedicationOrderEntity extends OrderEntity {
    
    @OneToMany(mappedBy = "medicationOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicineEntity> medications;
    
    public MedicationOrderEntity() {
        super();
    }

    public MedicationOrderEntity(String numeroOrden, PatientEntity patient, UserEntity doctor, LocalDateTime fecha) {
        super(numeroOrden, patient, doctor, fecha);
    }

    public void addMedication(MedicineEntity medication) {
        validateItemNumber(medication.getItemNumber());
        medication.setMedicationOrder(this);
        medications.add(medication);
    }

    public List<MedicineEntity> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicineEntity> medications) {
        this.medications = medications;
    }
    
    
}
