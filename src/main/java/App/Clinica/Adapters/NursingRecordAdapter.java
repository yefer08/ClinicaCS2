package App.Clinica.Adapters;

import App.Clinica.Entities.NursingRecordEntity;
import App.Clinica.Ports.NursingRecordPort;
import App.Clinica.Repositories.NursingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Adapter implementation for nursing record operations.
 * Handles the persistence and retrieval of nursing records.
 */
@Component
public class NursingRecordAdapter implements NursingRecordPort {

    private final NursingRecordRepository repository;

    @Autowired
    public NursingRecordAdapter(NursingRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public @NonNull Optional<NursingRecordEntity> findById(@NonNull Long id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error finding nursing record by ID: " + id, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<NursingRecordEntity> findByPatientIdPatient(String patientId) {
        try {
            return repository.findByPatientIdPatient(patientId);
        } catch (Exception e) {
            throw new RuntimeException("Error finding nursing records for patient: " + patientId, e);
        }
    }

    @Override
    @Transactional
    public NursingRecordEntity save(NursingRecordEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error saving nursing record", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting nursing record: " + id, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<NursingRecordEntity> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all nursing records", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NursingRecordEntity> findAll(Pageable pageable) {
        try {
            return repository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error finding nursing records with pagination", e);
        }
    }

    @Override
    @Transactional
    public @NonNull <S extends NursingRecordEntity> List<S> saveAll(Iterable<S> entities) {
        try {
            return repository.saveAll(entities);
        } catch (Exception e) {
            throw new RuntimeException("Error saving multiple nursing records", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return repository.count();
        } catch (Exception e) {
            throw new RuntimeException("Error counting nursing records", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        try {
            return repository.existsById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error checking existence of nursing record with id: " + id, e);
        }
    }

    @Override
    @NonNull
    public <S extends NursingRecordEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return repository.findBy(example, queryFunction);
    }

    @Override
    public void flush() {
        repository.flush();
    }

    @Override
    public <S extends NursingRecordEntity> S saveAndFlush(S entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public <S extends NursingRecordEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return repository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<NursingRecordEntity> entities) {
        repository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    @Override
    public NursingRecordEntity getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public NursingRecordEntity getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public NursingRecordEntity getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public <S extends NursingRecordEntity> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    @Override
    public <S extends NursingRecordEntity> List<S> findAll(Example<S> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    @Override
    public List<NursingRecordEntity> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void delete(NursingRecordEntity entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends NursingRecordEntity> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<NursingRecordEntity> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public <S extends NursingRecordEntity> Optional<S> findOne(Example<S> example) {
        return repository.findOne(example);
    }

    @Override
    public <S extends NursingRecordEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    @Override
    public <S extends NursingRecordEntity> long count(Example<S> example) {
        return repository.count(example);
    }

    @Override
    public <S extends NursingRecordEntity> boolean exists(Example<S> example) {
        return repository.exists(example);
    }
} 