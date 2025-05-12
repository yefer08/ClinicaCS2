package App.Clinica.Ports;

import App.Clinica.Entities.NursingInterventionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NursingInterventionPort extends JpaRepository<NursingInterventionEntity, Long> {
    List<NursingInterventionEntity> findByNursingRecordId(Long nursingRecordId);
} 