/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Record {
    private String IDDoctor;
    private String reasonQuery;
    private String symptoms;
    private String diagnosis;
    // Pueden agregarse órdenes vinculadas aquí si se desea
}
