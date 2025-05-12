package App.Clinica.Repositories;

import App.Clinica.Entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByPatientIdPatient(String patientId);
} 