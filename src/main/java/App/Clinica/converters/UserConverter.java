/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.converters;

import App.Clinica.Entities.UserEntity;
import App.Clinica.Models.User;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class UserConverter {
    public static UserEntity convertToEntity(User model) {
        if (model == null) return null;
        return new UserEntity(
            model.getFullName(),
            model.getCedule(),
            model.getEmail(),
            String.valueOf(model.getCellPhone()),
            LocalDate.parse(model.getBirthdate()),
            model.getAddress(),
            model.getRole(),
            model.getUserName(),
            model.getPassword()
        );
    }

    public static User convertToModel(UserEntity entity) {
        if (entity == null) return null;
        return new User(
            entity.getFullName(),
            entity.getCedule(),
            entity.getEmail(),
            Integer.parseInt(entity.getCellphone()),
            entity.getBirthdate().toString(),
            entity.getAddress(),
            entity.getRole(),
            entity.getUsername(),
            entity.getPassword()
        );
    }
}
