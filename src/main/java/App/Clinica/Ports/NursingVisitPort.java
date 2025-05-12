package App.Clinica.Ports;

import App.Clinica.Entities.NursingVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NursingVisitPort extends JpaRepository<NursingVisitEntity, Long> {
    List<NursingVisitEntity> findByOrderNumeroOrden(String orderId);
} 