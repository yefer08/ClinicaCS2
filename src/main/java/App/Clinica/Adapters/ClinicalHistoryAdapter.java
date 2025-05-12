package App.Clinica.Adapters;

import App.Clinica.Entities.ClinicalHistoryEntity;
import App.Clinica.Ports.ClinicalHistoryPort;
import App.Clinica.Repositories.ClinicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ClinicalHistoryAdapter implements ClinicalHistoryPort {

    private final ClinicalHistoryRepository clinicalHistoryRepository;

    @Autowired
    public ClinicalHistoryAdapter(ClinicalHistoryRepository clinicalHistoryRepository) {
        this.clinicalHistoryRepository = clinicalHistoryRepository;
    }

    @Override
    public List<ClinicalHistoryEntity> findByPatientId(String patientId) {
        return clinicalHistoryRepository.findByPatientIdPatient(patientId);
    }

    @Override
    public Optional<ClinicalHistoryEntity> findById(String id) {
        return clinicalHistoryRepository.findById(id);
    }

    @Override
    public ClinicalHistoryEntity save(ClinicalHistoryEntity history) {
        return clinicalHistoryRepository.save(history);
    }

    @Override
    public void deleteById(String id) {
        clinicalHistoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return clinicalHistoryRepository.existsById(id);
    }
} 