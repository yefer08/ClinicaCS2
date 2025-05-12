/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Ports;

import App.Clinica.Entities.PatientEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface PatientPort {

        boolean existsById(String id);

        PatientEntity save(PatientEntity patient);

        Optional<PatientEntity> findById(String id);

        void deleteById(String id);

        List<PatientEntity> findAll();
    
}
