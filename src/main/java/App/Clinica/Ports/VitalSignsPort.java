package App.Clinica.Ports;

import App.Clinica.Entities.VitalSignsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

@Repository
public interface VitalSignsPort extends JpaRepository<VitalSignsEntity, Long> {
    @Override
    @NonNull
    List<VitalSignsEntity> findAll();

    @Override
    @NonNull
    Optional<VitalSignsEntity> findById(@NonNull Long id);

    @Override
    @NonNull
    <S extends VitalSignsEntity> S save(@NonNull S vitalSigns);

    @Override
    void deleteById(@NonNull Long id);

    @Override
    boolean existsById(@NonNull Long id);

    List<VitalSignsEntity> findByPatientIdPatient(String patientId);
} 