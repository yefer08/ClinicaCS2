package App.Clinica.Services;

import App.Clinica.Models.Role;
import App.Clinica.Entities.UserEntity;
import App.Clinica.Ports.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserPort userPort;

    // Credenciales predeterminadas para Recursos Humanos
    private static final String DEFAULT_HR_USERNAME = "hr_admin";
    private static final String DEFAULT_HR_PASSWORD = "hr123456";
    
    public UserEntity authenticate(String username, String password) {
        // Primero verificar si es el usuario predeterminado de HR
        if (DEFAULT_HR_USERNAME.equals(username) && DEFAULT_HR_PASSWORD.equals(password)) {
            UserEntity hrUser = new UserEntity();
            hrUser.setRole(Role.HUMAN_RESOURCES);
            return hrUser;
        }

        // Si no es el usuario predeterminado, buscar en la base de datos
        return userPort.findByUsernameAndPassword(username, password)
            .orElse(null);
    }
} 