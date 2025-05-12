package App.Clinica.Ports;

import App.Clinica.Entities.ProceduresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

public interface ProceduresPort extends JpaRepository<ProceduresEntity, String> {
    @Override
    @NonNull
    List<ProceduresEntity> findAll();
    @Override
    @NonNull
    Optional<ProceduresEntity> findById(@NonNull String id);
    @Override
    @NonNull
    <S extends ProceduresEntity> S save(@NonNull S procedure);
    @Override
    void deleteById(@NonNull String id);
    @Override
    boolean existsById(@NonNull String id);
} 