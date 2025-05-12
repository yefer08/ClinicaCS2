package App.Clinica.Adapters;

import App.Clinica.Entities.NursingInterventionEntity;
import App.Clinica.Ports.NursingInterventionPort;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Component
public class NursingInterventionAdapter {
    private final JpaRepository<NursingInterventionEntity, Long> repository;

    public NursingInterventionAdapter(JpaRepository<NursingInterventionEntity, Long> repository) {
        this.repository = repository;
    }

    public NursingInterventionEntity save(NursingInterventionEntity intervention) {
        return repository.save(intervention);
    }

    public List<NursingInterventionEntity> findByNursingRecordId(Long nursingRecordId) {
        if (repository instanceof NursingInterventionPort) {
            return ((NursingInterventionPort) repository).findByNursingRecordId(nursingRecordId);
        }
        throw new UnsupportedOperationException("Repository does not implement NursingInterventionPort");
    }
} 