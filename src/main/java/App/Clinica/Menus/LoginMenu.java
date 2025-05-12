package App.Clinica.Menus;

import App.Clinica.Models.Role;
import App.Clinica.Services.AuthService;
import App.Clinica.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LoginMenu {
    @Autowired
    private AuthService authService;
    
    @Autowired
    private HumanResourcesMenu hrMenu;
    
    @Autowired
    private DoctorMenu doctorMenu;
    
    @Autowired
    private NurseMenu nurseMenu;
    
    @Autowired
    private AdministrativeMenu adminMenu;
    
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n=== SISTEMA CLÍNICO ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            
            int option = Integer.parseInt(scanner.nextLine());
            
            switch (option) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void handleLogin() {
        System.out.println("\n=== INICIO DE SESIÓN ===");
        System.out.print("Usuario: ");
        String username = scanner.nextLine();
        
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        
        UserEntity user = authService.authenticate(username, password);
        
        if (user != null) {
            System.out.println("Inicio de sesión exitoso!");
            redirectToMenu(user.getRole());
        } else {
            System.out.println("Credenciales inválidas. Intente nuevamente.");
        }
    }

    private void redirectToMenu(Role role) {
        switch (role) {
            case HUMAN_RESOURCES:
                hrMenu.display();
                break;
            case DOCTOR:
                doctorMenu.display();
                break;
            case NURSE:
                nurseMenu.display();
                break;
            case ADMINISTRATIVE:
                adminMenu.display();
                break;
            default:
                System.out.println("Rol no válido");
        }
    }
} 