package App.Clinica.Adapters;

import App.Clinica.Entities.EmergencyContactEntity;
import App.Clinica.Ports.EmergencyContactPort;
import App.Clinica.Repositories.EmergencyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmergencyContactAdapter implements EmergencyContactPort {

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Override
    public EmergencyContactEntity save(EmergencyContactEntity contact) {
        return emergencyContactRepository.save(contact);
    }

    @Override
    public Optional<EmergencyContactEntity> findById(Long id) {
        return emergencyContactRepository.findById(id);
    }

    @Override
    public Optional<EmergencyContactEntity> findByPatientId(String patientId) {
        return emergencyContactRepository.findByPatientIdPatient(patientId);
    }

    @Override
    public boolean existsById(Long id) {
        return emergencyContactRepository.existsById(id);
    }

    @Override
    public boolean existsByPatientId(String patientId) {
        return emergencyContactRepository.existsByPatientIdPatient(patientId);
    }

    @Override
    public void deleteById(Long id) {
        emergencyContactRepository.deleteById(id);
    }
} 