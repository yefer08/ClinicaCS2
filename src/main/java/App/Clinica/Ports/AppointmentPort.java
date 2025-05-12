/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Ports;

import App.Clinica.Entities.AppointmentEntity;
import java.util.List;
import java.util.Optional;

public interface AppointmentPort {

    AppointmentEntity save(AppointmentEntity appointment);

    Optional<AppointmentEntity> findById(String id);

    List<AppointmentEntity> findAll();

    void deleteById(String id);

    boolean existsById(String id);
}
