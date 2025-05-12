/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Services;

import App.Clinica.Entities.UserEntity;
import App.Clinica.Factories.UserFactory;
import App.Clinica.Models.User;
import App.Clinica.Ports.UserPort;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserPort userport;

    public UserEntity createUser(User userRawData) {
        // Validación de cédula
        if (userRawData.getCedule() == 0) {
            throw new IllegalArgumentException("El usuario debe tener un número de cédula.");
        }
        if (userport.existsById(userRawData.getCedule())) {
            throw new RuntimeException("Usuario ya existe.");
        }

        // Validación de nombre completo
        if (userRawData.getFullName() == null || userRawData.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es requerido.");
        }

        // Validación de email
        if (userRawData.getEmail() == null || userRawData.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es requerido.");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(emailRegex, userRawData.getEmail())) {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }

        // Validación de teléfono
        String phoneStr = String.valueOf(userRawData.getCellPhone());
        if (phoneStr.length() < 1 || phoneStr.length() > 10) {
            throw new IllegalArgumentException("El número de teléfono debe tener entre 1 y 10 dígitos.");
        }

        // Validación de fecha de nacimiento
        if (userRawData.getBirthdate() == null || userRawData.getBirthdate().trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento es requerida.");
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(userRawData.getBirthdate(), formatter);
            LocalDate now = LocalDate.now();
            
            // Validar que la fecha no sea futura
            if (birthDate.isAfter(now)) {
                throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
            }
            
            // Validar edad máxima (150 años)
            LocalDate maxAge = now.minusYears(150);
            if (birthDate.isBefore(maxAge)) {
                throw new IllegalArgumentException("La fecha de nacimiento no puede ser mayor a 150 años.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha de nacimiento debe tener el formato DD/MM/YYYY.");
        }

        // Validación de dirección
        if (userRawData.getAddress() == null || userRawData.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es requerida.");
        }
        if (userRawData.getAddress().length() > 30) {
            throw new IllegalArgumentException("La dirección no puede exceder los 30 caracteres.");
        }

        // Validación de rol
        if (userRawData.getRole() == null) {
            throw new RuntimeException("El usuario debe tener un rol asignado.");
        }

        // Validación de nombre de usuario
        if (userRawData.getUserName() == null || userRawData.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario es requerido.");
        }
        if (userRawData.getUserName().length() > 15) {
            throw new IllegalArgumentException("El nombre de usuario no puede exceder los 15 caracteres.");
        }
        String usernameRegex = "^[a-zA-Z0-9]+$";
        if (!Pattern.matches(usernameRegex, userRawData.getUserName())) {
            throw new IllegalArgumentException("El nombre de usuario solo puede contener letras y números.");
        }

        // Validación de contraseña
        if (userRawData.getPassword() == null || userRawData.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es requerida.");
        }
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!Pattern.matches(passwordRegex, userRawData.getPassword())) {
            throw new IllegalArgumentException("La contraseña debe incluir al menos una mayúscula, un número, un carácter especial y tener mínimo 8 caracteres.");
        }

        UserEntity user = UserFactory.create(userRawData);
        return userport.save(user);
    }

    public void deleteUser(int cedule) {
        if (!userport.existsById(cedule)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userport.deleteById(cedule);
    }

    public UserEntity updateUser(int cedule, UserEntity newuser) {
        if (!userport.existsById(cedule)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        newuser.setCedule(cedule);
        return userport.save(newuser);
    }

    public UserEntity getUser(int cedule) {
        return userport.findById(cedule)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    public List<UserEntity> listUsers() {
        return userport.findAll();
    }
}
