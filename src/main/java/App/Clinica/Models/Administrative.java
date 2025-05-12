/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

/**
 *
 * @author User
 */
public class Administrative extends User {

    public Administrative() {
    }

    public Administrative(String fullName, int cedule, String email, int cellPhone, String birthdate, String address, Role role, String userName, String password) {
        super(fullName, cedule, email, cellPhone, birthdate, address, role, userName, password);
    }
    
}
