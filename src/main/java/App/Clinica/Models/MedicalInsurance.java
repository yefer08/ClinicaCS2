/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalInsurance {
    private String nameCompany;
    private String Policynumber;
    private boolean Policystatus = true;
    private Date Policyvalidity;

    public MedicalInsurance(String nameCompany, String Policynumber, Date Policyvalidity) {
        this.nameCompany = nameCompany;
        this.Policynumber = Policynumber;
        this.Policyvalidity = Policyvalidity;
    }
    
    
}
