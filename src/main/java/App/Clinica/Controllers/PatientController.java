/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Controllers;


import App.Clinica.Entities.PatientEntity;
import App.Clinica.Services.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private AdminService adminService;

    // Registrar nuevo paciente
    @PostMapping
    public ResponseEntity<?> registerPatient(@RequestBody PatientEntity patient, @RequestHeader("role") String role) {
        if (!role.equalsIgnoreCase("administrative")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }

        PatientEntity newPatient = adminService.registerPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
    }


    // Listar todos los pacientes
    @GetMapping
    public ResponseEntity<List<PatientEntity>> getAllPatients() {
        return ResponseEntity.ok(adminService.listAllPatients());
    }

    // Obtener paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatient(@PathVariable String id) {
        return ResponseEntity.ok(adminService.getPatientById(id));
    }

    // Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable String id, @RequestHeader("role") String role) {
        if (!role.equalsIgnoreCase("administrative")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }
        adminService.deletePatient(id);
        return ResponseEntity.ok("Paciente eliminado");
    }
}
