package App.Clinica.Menus;

import App.Clinica.Services.UserService;
import App.Clinica.Entities.UserEntity;
import App.Clinica.Models.User;
import App.Clinica.Models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;


@Component
public class HumanResourcesMenu extends BaseMenu {
    @Autowired
    private UserService userService;
    
    private Scanner scanner = new Scanner(System.in);

    public HumanResourcesMenu() {
        super("HUMAN_RESOURCES");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Menú Recursos Humanos ===");
        System.out.println("1. Crear Nuevo Usuario");
        System.out.println("2. Actualizar Usuario");
        System.out.println("3. Eliminar Usuario");
        System.out.println("4. Ver Lista de Usuarios");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void handleOption(int option) {
        try {
            switch (option) {
                case 1:
                    handleCreateUser();
                    break;
                case 2:
                    handleUpdateUser();
                    break;
                case 3:
                    handleDeleteUser();
                    break;
                case 4:
                    handleListUsers();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleCreateUser() {
        try {
            System.out.println("\n=== Crear Nuevo Usuario ===");
            User newUser = new User();
            
            System.out.print("Nombre completo: ");
            newUser.setFullName(scanner.nextLine());
            
            System.out.print("Cédula: ");
            newUser.setCedule(Integer.parseInt(scanner.nextLine()));
            
            System.out.print("Email: ");
            newUser.setEmail(scanner.nextLine());
            
            System.out.print("Teléfono: ");
            newUser.setCellPhone(scanner.nextLine());
            
            System.out.print("Fecha de nacimiento (DD/MM/YYYY): ");
            newUser.setBirthdate(scanner.nextLine());
            
            System.out.print("Dirección: ");
            newUser.setAddress(scanner.nextLine());
            
            System.out.println("Roles disponibles:");
            for (Role role : Role.values()) {
                System.out.println("- " + role);
            }
            System.out.print("Rol: ");
            newUser.setRole(Role.valueOf(scanner.nextLine().toUpperCase()));
            
            System.out.print("Nombre de usuario: ");
            newUser.setUserName(scanner.nextLine());
            
            System.out.print("Contraseña: ");
            newUser.setPassword(scanner.nextLine());
            
            userService.createUser(newUser);
            System.out.println("Usuario creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        }
    }

    private void handleUpdateUser() {
        try {
            System.out.println("\n=== Actualizar Usuario ===");
            System.out.print("Ingrese la cédula del usuario a actualizar: ");
            int cedule = Integer.parseInt(scanner.nextLine());
            
            UserEntity existingUser = userService.getUser(cedule);
            if (existingUser == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
            
            System.out.println("Datos actuales del usuario:");
            System.out.println(existingUser);
            
            System.out.print("Nuevo nombre completo (Enter para mantener): ");
            String fullName = scanner.nextLine();
            if (!fullName.isEmpty()) {
                existingUser.setFullName(fullName);
            }
            
            System.out.print("Nuevo email (Enter para mantener): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                existingUser.setEmail(email);
            }
            
            System.out.print("Nuevo teléfono (Enter para mantener): ");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) {
                existingUser.setCellphone(phone);
            }
            
            System.out.print("Nueva dirección (Enter para mantener): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                existingUser.setAddress(address);
            }
            
            System.out.print("Nuevo rol (Enter para mantener): ");
            String role = scanner.nextLine();
            if (!role.isEmpty()) {
                existingUser.setRole(Role.valueOf(role.toUpperCase()));
            }
            
            userService.updateUser(cedule, existingUser);
            System.out.println("Usuario actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    private void handleDeleteUser() {
        try {
            System.out.println("\n=== Eliminar Usuario ===");
            System.out.print("Ingrese la cédula del usuario a eliminar: ");
            int cedule = Integer.parseInt(scanner.nextLine());
            
            userService.deleteUser(cedule);
            System.out.println("Usuario eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    private void handleListUsers() {
        try {
            System.out.println("\n=== Lista de Usuarios ===");
            List<UserEntity> users = userService.listUsers();
            
            if (users.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }
            
            for (UserEntity user : users) {
                System.out.println("\nCédula: " + user.getCedule());
                System.out.println("Nombre: " + user.getFullName());
                System.out.println("Rol: " + user.getRole());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Teléfono: " + user.getCellphone());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
    }

    public void display() {
        while (true) {
            displayMenu();
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 0) {
                System.out.println("Saliendo del menú de Recursos Humanos...");
                return;
            }
            handleOption(option);
        }
    }
} 