package App.Clinica.Entities;

import jakarta.persistence.*;


@Entity
@Table(name = "procedures")
public class ProceduresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idProcedimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_procedure_id", nullable = false)
    private OrderProcedureEntity orderProcedure;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String frequency;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private boolean requiresSpecialist;

    @Column(nullable = true)
    private String specialistId;

    @Column(nullable = false)
    private String typeSpecialty;

    @Column(nullable = false)
    private int itemNumber;

    public ProceduresEntity() {}

    public ProceduresEntity(OrderProcedureEntity orderProcedure, int amount, String frequency, double cost, 
                          boolean requiresSpecialist, String typeSpecialty, String specialistId, int itemNumber) {
        validateFields(amount, frequency, cost, requiresSpecialist, typeSpecialty, specialistId);
        
        this.orderProcedure = orderProcedure;
        this.amount = amount;
        this.frequency = frequency;
        this.cost = cost;
        this.requiresSpecialist = requiresSpecialist;
        this.typeSpecialty = typeSpecialty;
        this.specialistId = requiresSpecialist ? specialistId : null;
        this.itemNumber = itemNumber;
    }

    private void validateFields(int amount, String frequency, double cost, 
                              boolean requiresSpecialist, String typeSpecialty, String specialistId) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        
        if (frequency == null || frequency.trim().isEmpty()) {
            throw new IllegalArgumentException("La frecuencia es requerida");
        }
        
        if (cost <= 0) {
            throw new IllegalArgumentException("El costo debe ser mayor a 0");
        }
        
        if (requiresSpecialist) {
            if (typeSpecialty == null || typeSpecialty.trim().isEmpty()) {
                throw new IllegalArgumentException("El tipo de especialidad es requerido cuando se necesita especialista");
            }
            if (specialistId == null || specialistId.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del especialista es requerido cuando se necesita especialista");
            }
        }
    }

    public void setRequiresSpecialist(boolean requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
        if (!requiresSpecialist) {
            this.specialistId = null;
        }
    }

    public void setSpecialistId(String specialistId) {
        if (requiresSpecialist && (specialistId == null || specialistId.trim().isEmpty())) {
            throw new IllegalArgumentException("El ID del especialista es requerido cuando se necesita especialista");
        }
        this.specialistId = requiresSpecialist ? specialistId : null;
    }

    public String getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(String idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public OrderProcedureEntity getOrderProcedure() {
        return orderProcedure;
    }

    public void setOrderProcedure(OrderProcedureEntity orderProcedure) {
        this.orderProcedure = orderProcedure;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTypeSpecialty() {
        return typeSpecialty;
    }

    public void setTypeSpecialty(String typeSpecialty) {
        this.typeSpecialty = typeSpecialty;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public boolean isRequiresSpecialist() {
        return requiresSpecialist;
    }

    public String getSpecialistId() {
        return specialistId;
    }
    
    
}
