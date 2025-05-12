package App.Clinica.Services;

import App.Clinica.Entities.*;
import App.Clinica.Ports.OrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderPort orderPort;

    public List<OrderEntity> getPatientOrders(String patientId) {
        return orderPort.findByPatientId(patientId);
    }

    public DiagnosticOrderEntity createDiagnosticOrder(String numeroOrden, PatientEntity patient, UserEntity doctor, 
                                                     List<DiagnosticHelpEntity> exams) {
        validateOrderNumber(numeroOrden);
        validateDiagnosticOrder(exams);
        
        DiagnosticOrderEntity order = new DiagnosticOrderEntity(numeroOrden, patient, doctor, LocalDateTime.now());
        exams.forEach(exam -> {
            order.validateItemNumber(exam.getItemNumber());
            exam.setDiagnosticOrder(order);
        });
        order.setExams(exams);
        
        return (DiagnosticOrderEntity) orderPort.save(order);
    }

    public MedicationOrderEntity createMedicationOrder(String numeroOrden, PatientEntity patient, UserEntity doctor,
                                                     List<MedicineEntity> medications) {
        validateOrderNumber(numeroOrden);
        validateMedicationOrder(medications);
        
        MedicationOrderEntity order = new MedicationOrderEntity(numeroOrden, patient, doctor, LocalDateTime.now());
        medications.forEach(med -> {
            order.validateItemNumber(med.getItemNumber());
            med.setMedicationOrder(order);
        });
        order.setMedications(medications);
        
        return (MedicationOrderEntity) orderPort.save(order);
    }

    public OrderProcedureEntity createProcedureOrder(String numeroOrden, PatientEntity patient, UserEntity doctor,
                                                   List<ProceduresEntity> procedures) {
        validateOrderNumber(numeroOrden);
        validateProcedureOrder(procedures);
        
        OrderProcedureEntity order = new OrderProcedureEntity(numeroOrden, patient, doctor, LocalDateTime.now());
        procedures.forEach(proc -> {
            order.validateItemNumber(proc.getItemNumber());
            proc.setOrderProcedure(order);
        });
        order.setProcedures(procedures);
        
        return (OrderProcedureEntity) orderPort.save(order);
    }

    private void validateOrderNumber(String numeroOrden) {
        if (numeroOrden == null || numeroOrden.length() > 6) {
            throw new IllegalArgumentException("El número de orden no puede exceder 6 dígitos");
        }
        if (orderPort.existsById(numeroOrden)) {
            throw new IllegalArgumentException("El número de orden ya existe");
        }
    }

    private void validateDiagnosticOrder(List<DiagnosticHelpEntity> exams) {
        if (exams == null || exams.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar al menos un examen");
        }
    }

    private void validateMedicationOrder(List<MedicineEntity> medications) {
        if (medications == null || medications.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar al menos un medicamento");
        }
    }

    private void validateProcedureOrder(List<ProceduresEntity> procedures) {
        if (procedures == null || procedures.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar al menos un procedimiento");
        }
    }

    public double calculateOrderTotal(OrderEntity order) {
        double total = 0;
        
        if (order instanceof MedicationOrderEntity medOrder) {
            total += medOrder.getMedications().stream()
                .mapToDouble(MedicineEntity::getCost)
                .sum();
        }
        
        if (order instanceof OrderProcedureEntity procOrder) {
            total += procOrder.getProcedures().stream()
                .mapToDouble(ProceduresEntity::getCost)
                .sum();
        }
        
        if (order instanceof DiagnosticOrderEntity diagOrder) {
            total += diagOrder.getExams().stream()
                .mapToDouble(DiagnosticHelpEntity::getCost)
                .sum();
        }
        
        return total;
    }
} 