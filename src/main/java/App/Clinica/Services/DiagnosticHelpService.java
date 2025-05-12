package App.Clinica.Services;

import App.Clinica.Entities.DiagnosticHelpEntity;
import App.Clinica.Ports.DiagnosticHelpPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticHelpService {
    @Autowired
    private DiagnosticHelpPort diagnosticHelpPort;

    public DiagnosticHelpEntity getDiagnosticHelpById(String id) {
        return diagnosticHelpPort.findById(id).orElse(null);
    }

    public List<DiagnosticHelpEntity> getAllDiagnosticHelps() {
        return diagnosticHelpPort.findAll();
    }

    public DiagnosticHelpEntity saveDiagnosticHelp(DiagnosticHelpEntity diagnosticHelp) {
        return diagnosticHelpPort.save(diagnosticHelp);
    }

    public void deleteDiagnosticHelp(String id) {
        diagnosticHelpPort.deleteById(id);
    }
} 