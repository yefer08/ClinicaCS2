package App.Clinica.Ports;

import App.Clinica.Entities.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

public interface MedicinePort extends JpaRepository<MedicineEntity, String> {
    @Override
    @NonNull
    List<MedicineEntity> findAll();
    @Override
    @NonNull
    Optional<MedicineEntity> findById(@NonNull String id);
    @Override
    @NonNull
    <S extends MedicineEntity> S save(@NonNull S medicine);
    @Override
    void deleteById(@NonNull String id);
    @Override
    boolean existsById(@NonNull String id);
} 