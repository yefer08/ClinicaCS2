package App.Clinica.Ports;

import App.Clinica.Entities.NursingRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

@Repository
public interface NursingRecordPort extends JpaRepository<NursingRecordEntity, Long> {
    @Override
    @NonNull
    List<NursingRecordEntity> findAll();

    @Override
    @NonNull
    Optional<NursingRecordEntity> findById(@NonNull Long id);

    @Override
    @NonNull
    <S extends NursingRecordEntity> S save(@NonNull S nursingRecord);

    @Override
    void deleteById(@NonNull Long id);

    @Override
    boolean existsById(@NonNull Long id);

    List<NursingRecordEntity> findByPatientIdPatient(String patientId);
} 