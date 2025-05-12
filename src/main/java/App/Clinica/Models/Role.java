/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

/**
 *
 * @author User
 */
public enum Role {
    DOCTOR ,
    NURSE,
    ADMINISTRATIVE,
    HUMAN_RESOURCES;

    public static Role fromString(String rol) {
        return switch (rol.toLowerCase()) {
            case "doctor" ->
                DOCTOR;
            case "nurse" ->
                NURSE;
            case "person administrative", "administrative" ->
                ADMINISTRATIVE;
            case "human resources", "rrhh" ->
                HUMAN_RESOURCES;
            default ->
                throw new IllegalArgumentException("Error: Rol no válido -> " + rol);
        };
    }
}

