package App.Clinica.converters;

import App.Clinica.Entities.UserEntity;
import App.Clinica.Models.Doctor;
import App.Clinica.Models.Role;
import java.time.LocalDate;

public class DoctorConverter {
    public static Doctor convertToModel(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        if (entity.getRole() != Role.DOCTOR) {
            throw new IllegalArgumentException("User is not a doctor");
        }
        return new Doctor(
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

    public static UserEntity convertToEntity(Doctor model) {
        if (model == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setFullName(model.getFullName());
        entity.setCedule(model.getCedule());
        entity.setEmail(model.getEmail());
        entity.setCellphone(String.valueOf(model.getCellPhone()));
        entity.setBirthdate(LocalDate.parse(model.getBirthdate()));
        entity.setAddress(model.getAddress());
        entity.setRole(model.getRole());
        entity.setUsername(model.getUserName());
        entity.setPassword(model.getPassword());
        return entity;
    }
} 