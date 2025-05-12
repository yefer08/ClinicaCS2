/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Controllers;

import App.Clinica.Entities.UserEntity;
import App.Clinica.Models.User;
import App.Clinica.Services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/User")
public class UserController {
    @Autowired
    private UserService service;

    // Crear usuario
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user, @RequestHeader("role") String role) {
        if (!role.equalsIgnoreCase("Human Resources")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }

        UserEntity newUser = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Eliminar
    @DeleteMapping("/{cedule}")
    public ResponseEntity<?> deleteUser(@PathVariable int cedule, @RequestHeader("role") String role) {
        if (!role.equalsIgnoreCase("Human Resources")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }
        service.deleteUser(cedule);
        return ResponseEntity.ok("Usuario eliminado");
    }

    // Actualizar
    @PutMapping("/{cedule}")
    public ResponseEntity<?> updateUser(@PathVariable int cedule, @RequestBody UserEntity newuser, @RequestHeader("role") String role) {
        if (!role.equalsIgnoreCase("Human Resources")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }
        return ResponseEntity.ok(service.updateUser(cedule, newuser));
    }

    // Obtener uno
    @GetMapping("/{cedule}")
    public ResponseEntity<UserEntity> getUser(@PathVariable int cedule) {
        return ResponseEntity.ok(service.getUser(cedule));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers() {
        return ResponseEntity.ok(service.listUsers());
    }
}
