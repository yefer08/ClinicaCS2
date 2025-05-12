package App.Clinica.Services;

import App.Clinica.Entities.ProceduresEntity;
import App.Clinica.Ports.ProceduresPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProceduresService {
    @Autowired
    private ProceduresPort proceduresPort;

    public ProceduresEntity getProcedureById(String id) {
        return proceduresPort.findById(id).orElse(null);
    }

    public List<ProceduresEntity> getAllProcedures() {
        return proceduresPort.findAll();
    }

    public ProceduresEntity saveProcedure(ProceduresEntity procedure) {
        return proceduresPort.save(procedure);
    }

    public void deleteProcedure(String id) {
        proceduresPort.deleteById(id);
    }
} 