package App.Clinica.Entities;

import java.util.List;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity
@DiscriminatorValue("PROCEDURE")
public class OrderProcedureEntity extends OrderEntity {
    
    @OneToMany(mappedBy = "orderProcedure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProceduresEntity> procedures;
    
    public OrderProcedureEntity() {
        super();
    }

    public OrderProcedureEntity(String numeroOrden, PatientEntity patient, UserEntity doctor, LocalDateTime fecha) {
        super(numeroOrden, patient, doctor, fecha);
    }

    public void addProcedure(ProceduresEntity procedure) {
        validateItemNumber(procedure.getItemNumber());
        procedure.setOrderProcedure(this);
        procedures.add(procedure);
    }

    public List<ProceduresEntity> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProceduresEntity> procedures) {
        this.procedures = procedures;
    }
    
}
