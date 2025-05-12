/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Controllers;

import App.Clinica.Entities.AppointmentEntity;
import App.Clinica.Entities.PatientEntity;
import App.Clinica.Services.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AdminService adminService;

    // Programar una cita
    @PostMapping
    public ResponseEntity<?> registerPatient(@RequestBody PatientEntity patient, @RequestHeader("role") String role) {
        if (!role.equalsIgnoreCase("administrative")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }

        PatientEntity newPatient = adminService.registerPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
    }

    // Listar citas
    @GetMapping
    public ResponseEntity<List<AppointmentEntity>> getAppointments() {
        return ResponseEntity.ok(adminService.listAppointments());
    }
}
