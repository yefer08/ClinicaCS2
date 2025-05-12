package App.Clinica.Adapters;

import App.Clinica.Entities.InsuranceEntity;
import App.Clinica.Ports.InsurancePort;
import App.Clinica.Repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InsuranceAdapter implements InsurancePort {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public InsuranceEntity save(InsuranceEntity insurance) {
        return insuranceRepository.save(insurance);
    }

    @Override
    public Optional<InsuranceEntity> findById(Long id) {
        return insuranceRepository.findById(id);
    }

    @Override
    public Optional<InsuranceEntity> findByPatientId(String patientId) {
        return insuranceRepository.findByPatientIdPatient(patientId);
    }

    @Override
    public boolean existsById(Long id) {
        return insuranceRepository.existsById(id);
    }

    @Override
    public boolean existsByPatientId(String patientId) {
        return insuranceRepository.existsByPatientIdPatient(patientId);
    }

    @Override
    public void deleteById(Long id) {
        insuranceRepository.deleteById(id);
    }
} 