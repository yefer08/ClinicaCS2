/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class User {

    private String fullName;
    @GeneratedValue(strategy = GenerationType.UUID)
    private int cedule;
    private String email;
    private int cellPhone;
    private String birthdate;
    private String address;
    private Role role;
    private String userName;
    private String password;

    public User() {
    }

    public User(String fullName, int cedule, String email, int cellPhone, String birthdate, String address,
            Role role, String userName, String password) {
        this.fullName = fullName;
        this.cedule = cedule;
        this.email = email;
        this.cellPhone = cellPhone;
        this.birthdate = birthdate;
        this.address = address;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCedule() {
        return cedule;
    }

    public void setCedule(int cedule) {
        this.cedule = cedule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(int cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

}
