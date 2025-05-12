/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Adapters;
import App.Clinica.Entities.AppointmentEntity;
import App.Clinica.Ports.AppointmentPort;
import App.Clinica.Repositories.AppointmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentAdapter implements AppointmentPort {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AppointmentEntity save(AppointmentEntity appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Optional<AppointmentEntity> findById(String id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<AppointmentEntity> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return appointmentRepository.existsById(id);
    }
}
