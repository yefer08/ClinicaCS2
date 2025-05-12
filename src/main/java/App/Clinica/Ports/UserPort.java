/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Clinica.Ports;

import App.Clinica.Entities.UserEntity;
import java.util.List;
import java.util.Optional;


public interface UserPort {

    boolean existsById(int cedule);

    Optional<UserEntity> findById(int cedule);

    UserEntity save(UserEntity user);

    void deleteById(int cedule);

    List<UserEntity> findAll();
    
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
