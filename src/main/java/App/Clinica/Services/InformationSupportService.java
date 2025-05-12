package App.Clinica.Services;

import App.Clinica.Entities.*;
import App.Clinica.Ports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InformationSupportService {

    @Autowired
    private MedicinePort medicinePort;

    @Autowired
    private ProceduresPort proceduresPort;

    @Autowired
    private DiagnosticHelpPort diagnosticHelpPort;

    // Medicamentos
    public MedicineEntity addMedicine(MedicineEntity medicine) {
        return medicinePort.save(medicine);
    }

    public List<MedicineEntity> getAllMedicines() {
        return medicinePort.findAll();
    }

    public MedicineEntity updateMedicine(String id, MedicineEntity medicine) {
        if (!medicinePort.existsById(id)) {
            throw new IllegalArgumentException("Medicamento no encontrado");
        }
        medicine.setIdMedication(id);
        return medicinePort.save(medicine);
    }

    public void deleteMedicine(String id) {
        if (!medicinePort.existsById(id)) {
            throw new IllegalArgumentException("Medicamento no encontrado");
        }
        medicinePort.deleteById(id);
    }

    // Procedimientos
    public ProceduresEntity addProcedure(ProceduresEntity procedure) {
        return proceduresPort.save(procedure);
    }

    public List<ProceduresEntity> getAllProcedures() {
        return proceduresPort.findAll();
    }

    public ProceduresEntity updateProcedure(String id, ProceduresEntity procedure) {
        if (!proceduresPort.existsById(id)) {
            throw new IllegalArgumentException("Procedimiento no encontrado");
        }
        procedure.setIdProcedimiento(id);
        return proceduresPort.save(procedure);
    }

    public void deleteProcedure(String id) {
        if (!proceduresPort.existsById(id)) {
            throw new IllegalArgumentException("Procedimiento no encontrado");
        }
        proceduresPort.deleteById(id);
    }

    // Ayudas Diagnósticas
    public DiagnosticHelpEntity addDiagnosticHelp(DiagnosticHelpEntity diagnosticHelp) {
        return diagnosticHelpPort.save(diagnosticHelp);
    }

    public List<DiagnosticHelpEntity> getAllDiagnosticHelps() {
        return diagnosticHelpPort.findAll();
    }

    public DiagnosticHelpEntity updateDiagnosticHelp(String id, DiagnosticHelpEntity diagnosticHelp) {
        if (!diagnosticHelpPort.existsById(id)) {
            throw new IllegalArgumentException("Ayuda diagnóstica no encontrada");
        }
        diagnosticHelp.setIdDiagnostico(id);
        return diagnosticHelpPort.save(diagnosticHelp);
    }

    public void deleteDiagnosticHelp(String id) {
        if (!diagnosticHelpPort.existsById(id)) {
            throw new IllegalArgumentException("Ayuda diagnóstica no encontrada");
        }
        diagnosticHelpPort.deleteById(id);
    }

    // Métodos de validación
    public boolean validateMedicineData(MedicineEntity medicine) {
        return medicine.getDose() != null && !medicine.getDose().isEmpty() &&
               medicine.getDuration() > 0 &&
               medicine.getCost() >= 0;
    }

    public boolean validateProcedureData(ProceduresEntity procedure) {
        return procedure.getAmount() > 0 &&
               procedure.getFrequency() != null && !procedure.getFrequency().isEmpty() &&
               procedure.getCost() >= 0 &&
               procedure.getTypeSpecialty() != null && !procedure.getTypeSpecialty().isEmpty();
    }

    public boolean validateDiagnosticHelpData(DiagnosticHelpEntity diagnosticHelp) {
        return diagnosticHelp.getAmount() > 0 &&
               diagnosticHelp.getCost() >= 0 &&
               diagnosticHelp.getTypeSpecialty() != null && !diagnosticHelp.getTypeSpecialty().isEmpty();
    }
} 