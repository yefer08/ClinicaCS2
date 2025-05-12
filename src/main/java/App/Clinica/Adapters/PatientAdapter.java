/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Adapters;

import App.Clinica.Entities.PatientEntity;
import App.Clinica.Ports.PatientPort;
import App.Clinica.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
@Component
public class PatientAdapter implements PatientPort {
    private final PatientRepository repository;

    @Autowired
    public PatientAdapter(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientEntity> findById(String id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public PatientEntity save(PatientEntity patient) {
        return repository.save(patient);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientEntity> findAll() {
        return repository.findAll();
    }
}
