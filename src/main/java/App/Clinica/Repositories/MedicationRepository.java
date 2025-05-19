package App.Clinica.Repositories;

import App.Clinica.Entities.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationEntity, Integer> {
    // Métodos personalizados si son necesarios
} 