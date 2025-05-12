package App.Clinica.Menus;

import App.Clinica.Services.InformationSupportService;
import App.Clinica.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class InformationSupportMenu {
    @Autowired
    private InformationSupportService informationSupportService;
    
    private Scanner scanner = new Scanner(System.in);

    public InformationSupportMenu() {
    }

    public void display() {
        while (true) {
            System.out.println("\n=== MENÚ SOPORTE INFORMACIÓN ===");
            System.out.println("1. Gestionar Medicamentos");
            System.out.println("2. Gestionar Procedimientos");
            System.out.println("3. Gestionar Ayudas Diagnósticas");
            System.out.println("4. Generar Reporte de Inventario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            int option = Integer.parseInt(scanner.nextLine());
            
            if (option == 5) {
                System.out.println("Saliendo del menú de soporte de información...");
                return;
            }
            
            handleOption(option);
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1:
                manageMedications();
                break;
            case 2:
                manageProcedures();
                break;
            case 3:
                manageDiagnosticAids();
                break;
            case 4:
                generateInventoryReport();
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private void manageMedications() {
        try {
            System.out.println("\n=== Gestión de Medicamentos ===");
            System.out.println("1. Agregar nuevo medicamento");
            System.out.println("2. Actualizar medicamento");
            System.out.println("3. Ver inventario de medicamentos");
            System.out.print("Seleccione una opción: ");
            
            int option = Integer.parseInt(scanner.nextLine());
            
            switch (option) {
                case 1:
                    handleAddMedicine();
                    break;
                case 2:
                    handleUpdateMedicine();
                    break;
                case 3:
                    handleViewMedicines();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (Exception e) {
            System.out.println("Error al gestionar medicamentos: " + e.getMessage());
        }
    }

    private void handleAddMedicine() {
        try {
            System.out.println("\n=== Agregar Nuevo Medicamento ===");
            MedicineEntity newMedicine = new MedicineEntity();
            
            System.out.print("ID del medicamento: ");
            newMedicine.setIdMedication(scanner.nextLine());
            
            System.out.print("Dosis: ");
            newMedicine.setDose(scanner.nextLine());
            
            System.out.print("Duración (días): ");
            newMedicine.setDuration(Integer.parseInt(scanner.nextLine()));
            
            System.out.print("Costo: ");
            newMedicine.setCost(Double.parseDouble(scanner.nextLine()));
            
            System.out.print("Número de ítem: ");
            newMedicine.setItemNumber(Integer.parseInt(scanner.nextLine()));
            
            informationSupportService.addMedicine(newMedicine);
            System.out.println("Medicamento agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar medicamento: " + e.getMessage());
        }
    }

    private void handleUpdateMedicine() {
        try {
            System.out.println("\n=== Actualizar Medicamento ===");
            System.out.print("ID del medicamento: ");
            String id = scanner.nextLine();
            
            MedicineEntity medicine = informationSupportService.getAllMedicines().stream()
                .filter(m -> m.getIdMedication().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado"));
            
            System.out.println("Datos actuales del medicamento:");
            System.out.println(medicine);
            
            System.out.print("Nueva dosis (Enter para mantener): ");
            String dose = scanner.nextLine();
            if (!dose.isEmpty()) {
                medicine.setDose(dose);
            }
            
            System.out.print("Nueva duración (Enter para mantener): ");
            String duration = scanner.nextLine();
            if (!duration.isEmpty()) {
                medicine.setDuration(Integer.parseInt(duration));
            }
            
            System.out.print("Nuevo costo (Enter para mantener): ");
            String cost = scanner.nextLine();
            if (!cost.isEmpty()) {
                medicine.setCost(Double.parseDouble(cost));
            }
            
            System.out.print("Nuevo número de ítem (Enter para mantener): ");
            String itemNumber = scanner.nextLine();
            if (!itemNumber.isEmpty()) {
                medicine.setItemNumber(Integer.parseInt(itemNumber));
            }
            
            informationSupportService.updateMedicine(id, medicine);
            System.out.println("Medicamento actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar medicamento: " + e.getMessage());
        }
    }

    private void handleViewMedicines() {
        try {
            System.out.println("\n=== Inventario de Medicamentos ===");
            List<MedicineEntity> medicines = informationSupportService.getAllMedicines();
            
            if (medicines.isEmpty()) {
                System.out.println("No hay medicamentos registrados.");
                return;
            }
            
            for (MedicineEntity medicine : medicines) {
                System.out.println("\nID: " + medicine.getIdMedication());
                System.out.println("Dosis: " + medicine.getDose());
                System.out.println("Duración: " + medicine.getDuration() + " días");
                System.out.println("Costo: $" + medicine.getCost());
                System.out.println("Número de ítem: " + medicine.getItemNumber());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar medicamentos: " + e.getMessage());
        }
    }

    private void manageProcedures() {
        try {
            System.out.println("\n=== Gestión de Procedimientos ===");
            System.out.println("1. Agregar nuevo procedimiento");
            System.out.println("2. Actualizar procedimiento");
            System.out.println("3. Ver inventario de procedimientos");
            System.out.print("Seleccione una opción: ");
            
            int option = Integer.parseInt(scanner.nextLine());
            
            switch (option) {
                case 1:
                    handleAddProcedure();
                    break;
                case 2:
                    handleUpdateProcedure();
                    break;
                case 3:
                    handleViewProcedures();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (Exception e) {
            System.out.println("Error al gestionar procedimientos: " + e.getMessage());
        }
    }

    private void handleAddProcedure() {
        try {
            System.out.println("\n=== Agregar Nuevo Procedimiento ===");
            ProceduresEntity newProcedure = new ProceduresEntity();
            
            System.out.print("ID del procedimiento: ");
            newProcedure.setIdProcedimiento(scanner.nextLine());
            
            System.out.print("Cantidad: ");
            newProcedure.setAmount(Integer.parseInt(scanner.nextLine()));
            
            System.out.print("Frecuencia: ");
            newProcedure.setFrequency(scanner.nextLine());
            
            System.out.print("Costo: ");
            newProcedure.setCost(Double.parseDouble(scanner.nextLine()));
            
            System.out.print("Tipo de especialidad: ");
            newProcedure.setTypeSpecialty(scanner.nextLine());
            
            informationSupportService.addProcedure(newProcedure);
            System.out.println("Procedimiento agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar procedimiento: " + e.getMessage());
        }
    }

    private void handleUpdateProcedure() {
        try {
            System.out.println("\n=== Actualizar Procedimiento ===");
            System.out.print("ID del procedimiento: ");
            String id = scanner.nextLine();
            
            ProceduresEntity procedure = informationSupportService.getAllProcedures().stream()
                .filter(p -> p.getIdProcedimiento().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Procedimiento no encontrado"));
            
            System.out.println("Datos actuales del procedimiento:");
            System.out.println(procedure);
            
            System.out.print("Nueva cantidad (Enter para mantener): ");
            String amount = scanner.nextLine();
            if (!amount.isEmpty()) {
                procedure.setAmount(Integer.parseInt(amount));
            }
            
            System.out.print("Nueva frecuencia (Enter para mantener): ");
            String frequency = scanner.nextLine();
            if (!frequency.isEmpty()) {
                procedure.setFrequency(frequency);
            }
            
            System.out.print("Nuevo costo (Enter para mantener): ");
            String cost = scanner.nextLine();
            if (!cost.isEmpty()) {
                procedure.setCost(Double.parseDouble(cost));
            }
            
            System.out.print("Nuevo tipo de especialidad (Enter para mantener): ");
            String typeSpecialty = scanner.nextLine();
            if (!typeSpecialty.isEmpty()) {
                procedure.setTypeSpecialty(typeSpecialty);
            }
            
            informationSupportService.updateProcedure(id, procedure);
            System.out.println("Procedimiento actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar procedimiento: " + e.getMessage());
        }
    }

    private void handleViewProcedures() {
        try {
            System.out.println("\n=== Inventario de Procedimientos ===");
            List<ProceduresEntity> procedures = informationSupportService.getAllProcedures();
            
            if (procedures.isEmpty()) {
                System.out.println("No hay procedimientos registrados.");
                return;
            }
            
            for (ProceduresEntity procedure : procedures) {
                System.out.println("\nID: " + procedure.getIdProcedimiento());
                System.out.println("Cantidad: " + procedure.getAmount());
                System.out.println("Frecuencia: " + procedure.getFrequency());
                System.out.println("Costo: $" + procedure.getCost());
                System.out.println("Tipo de especialidad: " + procedure.getTypeSpecialty());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar procedimientos: " + e.getMessage());
        }
    }

    private void manageDiagnosticAids() {
        try {
            System.out.println("\n=== Gestión de Ayudas Diagnósticas ===");
            System.out.println("1. Agregar nueva ayuda diagnóstica");
            System.out.println("2. Actualizar ayuda diagnóstica");
            System.out.println("3. Ver inventario de ayudas diagnósticas");
            System.out.print("Seleccione una opción: ");
            
            int option = Integer.parseInt(scanner.nextLine());
            
            switch (option) {
                case 1:
                    handleAddDiagnosticHelp();
                    break;
                case 2:
                    handleUpdateDiagnosticHelp();
                    break;
                case 3:
                    handleViewDiagnosticHelps();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (Exception e) {
            System.out.println("Error al gestionar ayudas diagnósticas: " + e.getMessage());
        }
    }

    private void handleAddDiagnosticHelp() {
        try {
            System.out.println("\n=== Agregar Nueva Ayuda Diagnóstica ===");
            DiagnosticHelpEntity newHelp = new DiagnosticHelpEntity();
            
            System.out.print("ID de la ayuda diagnóstica: ");
            newHelp.setIdDiagnostico(scanner.nextLine());
            
            System.out.print("Costo: ");
            newHelp.setCost(Double.parseDouble(scanner.nextLine()));
            
            informationSupportService.addDiagnosticHelp(newHelp);
            System.out.println("Ayuda diagnóstica agregada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar ayuda diagnóstica: " + e.getMessage());
        }
    }

    private void handleUpdateDiagnosticHelp() {
        try {
            System.out.println("\n=== Actualizar Ayuda Diagnóstica ===");
            System.out.print("ID de la ayuda diagnóstica: ");
            String id = scanner.nextLine();
            
            DiagnosticHelpEntity help = informationSupportService.getAllDiagnosticHelps().stream()
                .filter(h -> h.getIdDiagnostico().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ayuda diagnóstica no encontrada"));
            
            System.out.println("Datos actuales de la ayuda diagnóstica:");
            System.out.println(help);
            
            System.out.print("Nuevo costo (Enter para mantener): ");
            String cost = scanner.nextLine();
            if (!cost.isEmpty()) {
                help.setCost(Double.parseDouble(cost));
            }
            
            informationSupportService.updateDiagnosticHelp(id, help);
            System.out.println("Ayuda diagnóstica actualizada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar ayuda diagnóstica: " + e.getMessage());
        }
    }

    private void handleViewDiagnosticHelps() {
        try {
            System.out.println("\n=== Inventario de Ayudas Diagnósticas ===");
            List<DiagnosticHelpEntity> helps = informationSupportService.getAllDiagnosticHelps();
            
            if (helps.isEmpty()) {
                System.out.println("No hay ayudas diagnósticas registradas.");
                return;
            }
            
            for (DiagnosticHelpEntity help : helps) {
                System.out.println("\nID: " + help.getIdDiagnostico());
                System.out.println("Costo: $" + help.getCost());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar ayudas diagnósticas: " + e.getMessage());
        }
    }

    private void generateInventoryReport() {
        try {
            System.out.println("\n=== Reportes de Inventario ===");
            System.out.println("1. Reporte de medicamentos");
            System.out.println("2. Reporte de procedimientos");
            System.out.println("3. Reporte de ayudas diagnósticas");
            System.out.print("Seleccione una opción: ");
            
            int option = Integer.parseInt(scanner.nextLine());
            
            switch (option) {
                case 1:
                    handleMedicineReport();
                    break;
                case 2:
                    handleProceduresReport();
                    break;
                case 3:
                    handleDiagnosticHelpReport();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (Exception e) {
            System.out.println("Error al generar reportes: " + e.getMessage());
        }
    }

    private void handleMedicineReport() {
        try {
            System.out.println("\n=== Reporte de Medicamentos ===");
            List<MedicineEntity> medicines = informationSupportService.getAllMedicines();
            
            if (medicines.isEmpty()) {
                System.out.println("No hay medicamentos registrados.");
                return;
            }
            
            double totalValue = 0;
            for (MedicineEntity medicine : medicines) {
                totalValue += medicine.getCost();
            }
            
            System.out.println("\nTotal de medicamentos: " + medicines.size());
            System.out.println("Valor total del inventario: $" + totalValue);
            System.out.println("\nDetalle por medicamento:");
            
            for (MedicineEntity medicine : medicines) {
                System.out.println("\nID: " + medicine.getIdMedication());
                System.out.println("Dosis: " + medicine.getDose());
                System.out.println("Duración: " + medicine.getDuration() + " días");
                System.out.println("Costo: $" + medicine.getCost());
                System.out.println("Número de ítem: " + medicine.getItemNumber());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al generar reporte de medicamentos: " + e.getMessage());
        }
    }

    private void handleProceduresReport() {
        try {
            System.out.println("\n=== Reporte de Procedimientos ===");
            List<ProceduresEntity> procedures = informationSupportService.getAllProcedures();
            
            if (procedures.isEmpty()) {
                System.out.println("No hay procedimientos registrados.");
                return;
            }
            
            double totalValue = 0;
            for (ProceduresEntity procedure : procedures) {
                totalValue += procedure.getCost();
            }
            
            System.out.println("\nTotal de procedimientos: " + procedures.size());
            System.out.println("Valor total del inventario: $" + totalValue);
            System.out.println("\nDetalle por procedimiento:");
            
            for (ProceduresEntity procedure : procedures) {
                System.out.println("\nID: " + procedure.getIdProcedimiento());
                System.out.println("Cantidad: " + procedure.getAmount());
                System.out.println("Frecuencia: " + procedure.getFrequency());
                System.out.println("Costo: $" + procedure.getCost());
                System.out.println("Tipo de especialidad: " + procedure.getTypeSpecialty());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al generar reporte de procedimientos: " + e.getMessage());
        }
    }

    private void handleDiagnosticHelpReport() {
        try {
            System.out.println("\n=== Reporte de Ayudas Diagnósticas ===");
            List<DiagnosticHelpEntity> helps = informationSupportService.getAllDiagnosticHelps();
            
            if (helps.isEmpty()) {
                System.out.println("No hay ayudas diagnósticas registradas.");
                return;
            }
            
            double totalValue = 0;
            for (DiagnosticHelpEntity help : helps) {
                totalValue += help.getCost();
            }
            
            System.out.println("\nTotal de ayudas diagnósticas: " + helps.size());
            System.out.println("Valor total del inventario: $" + totalValue);
            System.out.println("\nDetalle por ayuda diagnóstica:");
            
            for (DiagnosticHelpEntity help : helps) {
                System.out.println("\nID: " + help.getIdDiagnostico());
                System.out.println("Costo: $" + help.getCost());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al generar reporte de ayudas diagnósticas: " + e.getMessage());
        }
    }
} 