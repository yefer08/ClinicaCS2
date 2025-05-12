/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Patient {
    
    private String idPatient;
    private String fullName;
    private String birthdate;
    private String gender;
    private String address;
    private int cellPhone;
    private String email;

    public Patient(String idPatient, String fullName, String birthdate, String gender, String address, int cellPhone, String email) {
        this.idPatient = idPatient;
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.cellPhone = cellPhone;
        this.email = email;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(int cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    


}
