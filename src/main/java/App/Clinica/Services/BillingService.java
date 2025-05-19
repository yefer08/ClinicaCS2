package App.Clinica.Services;

import App.Clinica.Models.Billing;
import App.Clinica.Models.Insurance;
import App.Clinica.Models.Patient;
import App.Clinica.Models.User;
import App.Clinica.Entities.PatientEntity;
import App.Clinica.Entities.UserEntity;
import App.Clinica.Entities.InsuranceEntity;
import App.Clinica.Ports.InsurancePort;
import App.Clinica.Ports.PatientPort;
import App.Clinica.Ports.UserPort;
import App.Clinica.converters.InsuranceConverter;
import App.Clinica.converters.PatientConverter;
import App.Clinica.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class BillingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PatientPort patientPort;

    @Autowired
    private UserPort userPort;

    @Autowired
    private InsurancePort insurancePort;

    @Autowired
    private OrderService orderService;

    @Transactional(readOnly = true)
    public Billing generateBilling(String patientId, int doctorId) {
        // Obtener información del paciente
        PatientEntity patientEntity = patientPort.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
        // Inicializar la entidad dentro de la transacción
        entityManager.refresh(patientEntity);
        Patient patient = PatientConverter.convertToModel(patientEntity);

        // Obtener información del médico
        UserEntity doctorEntity = userPort.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"));
        // Inicializar la entidad dentro de la transacción
        entityManager.refresh(doctorEntity);
        User doctor = UserConverter.convertToModel(doctorEntity);

        // Obtener información del seguro
        InsuranceEntity insuranceEntity = insurancePort.findByPatientId(patientId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró información de seguro para el paciente"));
        // Inicializar la entidad dentro de la transacción
        entityManager.refresh(insuranceEntity);
        Insurance insurance = InsuranceConverter.convertToModel(insuranceEntity);

        // Crear objeto de facturación
        Billing billing = new Billing(patient, doctor, insurance);

        // Obtener y agregar información clínica
        orderService.getPatientOrders(patientId).forEach(order -> {
            billing.addOrder(order);
        });

        // Calcular pagos
        billing.calculatePayment();

        return billing;
    }
} 