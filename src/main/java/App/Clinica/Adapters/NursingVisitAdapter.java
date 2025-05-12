package App.Clinica.Adapters;

import App.Clinica.Entities.NursingVisitEntity;
import App.Clinica.Ports.NursingVisitPort;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class NursingVisitAdapter {
    private final JpaRepository<NursingVisitEntity, Long> repository;

    public NursingVisitAdapter(JpaRepository<NursingVisitEntity, Long> repository) {
        this.repository = repository;
    }

    @Transactional
    public NursingVisitEntity save(NursingVisitEntity visit) {
        try {
            return repository.save(visit);
        } catch (Exception e) {
            throw new RuntimeException("Error saving nursing visit", e);
        }
    }

    @Transactional(readOnly = true)
    public List<NursingVisitEntity> findByOrderId(String orderId) {
        try {
            if (repository instanceof NursingVisitPort) {
                return ((NursingVisitPort) repository).findByOrderNumeroOrden(orderId);
            }
            throw new UnsupportedOperationException("Repository does not implement NursingVisitPort");
        } catch (Exception e) {
            throw new RuntimeException("Error finding nursing visits for order: " + orderId, e);
        }
    }
} 