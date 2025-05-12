package App.Clinica.Services;

import App.Clinica.Entities.*;
import App.Clinica.Ports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NursingRecordService {
    @Autowired
    private NursingRecordPort nursingRecordPort;
    @Autowired
    private NursingInterventionPort nursingInterventionPort;
    @Autowired
    private NursingVisitPort nursingVisitPort;
    @Autowired
    private OrderPort orderPort;

    public NursingRecordEntity createNursingRecord(NursingRecordEntity record) {
        return nursingRecordPort.save(record);
    }

    public List<NursingRecordEntity> getPatientNursingRecords(String patientId) {
        return nursingRecordPort.findByPatientIdPatient(patientId);
    }

    public NursingInterventionEntity createNursingIntervention(NursingInterventionEntity intervention) {
        return nursingInterventionPort.save(intervention);
    }

    public List<NursingInterventionEntity> getRecordInterventions(Long recordId) {
        return nursingInterventionPort.findByNursingRecordId(recordId);
    }

    public NursingVisitEntity createNursingVisit(NursingVisitEntity visit, Long recordId) {
        OrderEntity order = orderPort.findById(recordId.toString())
            .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));
        visit.setOrder(order);
        return nursingVisitPort.save(visit);
    }

    public List<NursingVisitEntity> getRecordVisits(Long recordId) {
        NursingRecordEntity record = nursingRecordPort.findById(recordId)
            .orElseThrow(() -> new IllegalArgumentException("Nursing record not found"));
        return nursingVisitPort.findByOrderNumeroOrden(record.getOrder().getNumeroOrden());
    }
} 