package App.Clinica.Services;

import App.Clinica.Entities.MedicationEntity;
import App.Clinica.Repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Transactional(readOnly = true)
    public List<MedicationEntity> getAllMedications() {
        return medicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<MedicationEntity> getMedicationById(Integer id) {
        return medicationRepository.findById(id);
    }

    @Transactional
    public MedicationEntity saveMedication(MedicationEntity medication) {
        return medicationRepository.save(medication);
    }

    @Transactional
    public void deleteMedication(Integer id) {
        medicationRepository.deleteById(id);
    }
} 