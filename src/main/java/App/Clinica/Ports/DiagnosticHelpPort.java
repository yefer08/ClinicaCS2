package App.Clinica.Ports;

import App.Clinica.Entities.DiagnosticHelpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.lang.NonNull;

public interface DiagnosticHelpPort extends JpaRepository<DiagnosticHelpEntity, String> {
    @Override
    @NonNull
    List<DiagnosticHelpEntity> findAll();
    @Override
    @NonNull
    <S extends DiagnosticHelpEntity> S save(@NonNull S diagnosticHelp);
    @Override
    void deleteById(@NonNull String id);
    @Override
    boolean existsById(@NonNull String id);
} 