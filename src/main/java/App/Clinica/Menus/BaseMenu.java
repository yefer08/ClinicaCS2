package App.Clinica.Menus;

import java.util.Scanner;

public abstract class BaseMenu {
    protected Scanner scanner;
    protected String role;

    public BaseMenu(String role) {
        this.scanner = new Scanner(System.in);
        this.role = role;
    }

    public abstract void displayMenu();
    public abstract void handleOption(int option);

    public void display() {
        while (true) {
            displayMenu();
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 5) {
                System.out.println("Saliendo del menú...");
                return;
            }
            handleOption(option);
        }
    }
} 