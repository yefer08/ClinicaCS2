/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class ClinicalHistory {

    private User idDoctor;
    private Map<String, Record> registrosPorFecha = new HashMap<>();
    private String reason;
    private String Symptoms;
    private String Diagnosis;

   
}
