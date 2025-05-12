package App.Clinica.Ports;

import App.Clinica.Entities.OrderEntity;
import java.util.List;
import java.util.Optional;

public interface OrderPort {
    List<OrderEntity> findByPatientId(String patientId);
    Optional<OrderEntity> findById(String id);
    OrderEntity save(OrderEntity order);
    void deleteById(String id);
    boolean existsById(String id);
} 