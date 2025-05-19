package App.Clinica.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
public class MedicationEntity {

    @Id
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String dosageForm; // forma farmacéutica (tableta, jarabe, etc.)

    @Column(nullable = false)
    private String concentration; // concentración (e.g., "500 mg", "10 mg/ml")

    @Column(length = 1000)
    private String indications;

    @Column(length = 1000)
    private String contraindications;

    public MedicationEntity(Integer id, String name, String description, String dosageForm, 
                          String concentration, String indications, String contraindications) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dosageForm = dosageForm;
        this.concentration = concentration;
        this.indications = indications;
        this.contraindications = contraindications;
    }
} 