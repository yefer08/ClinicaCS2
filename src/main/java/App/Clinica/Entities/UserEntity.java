/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Entities;

import App.Clinica.Models.Role;
import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "users")
public class UserEntity {
    
    @Column(nullable = false)
    private String fullName;

    @Id
    private int cedule;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String cellphone;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public UserEntity() {}

    public UserEntity(String fullName, int cedule, String email, String cellphone, LocalDate birthdate, 
                     String address, Role role, String username, String password) {
        this.fullName = fullName;
        this.cedule = cedule;
        this.email = email;
        this.cellphone = cellphone;
        this.birthdate = birthdate;
        this.address = address;
        this.role = role;
        this.username = username;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
