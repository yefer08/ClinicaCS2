/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Factories;

import App.Clinica.Entities.UserEntity;
import App.Clinica.Models.Role;
import App.Clinica.Models.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserFactory {

    public static UserEntity create(User user) {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("El usuario debe tener un rol asignado");
        }

        Role role = user.getRole();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(user.getBirthdate(), formatter);

        return new UserEntity(
                user.getFullName(),
                user.getCedule(),
                user.getEmail(),
                String.valueOf(user.getCellPhone()),
                birthDate,
                user.getAddress(),
                role,
                user.getUserName(),
                user.getPassword()
        );
    }

    public static User create(String fullName, int cedule, String email, int cellPhone, String birthdate,
            String address, String roleText, String userName, String password) {
        Role role = Role.fromString(roleText);

        return new User(
                fullName,
                cedule,
                email,
                cellPhone,
                birthdate,
                address,
                role,
                userName,
                password
        );
    }

}
