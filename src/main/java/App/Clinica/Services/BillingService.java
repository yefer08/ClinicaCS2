package App.Clinica.Services;

import App.Clinica.Models.Billing;
import App.Clinica.Models.Insurance;
import App.Clinica.Models.Patient;
import App.Clinica.Models.User;
import App.Clinica.Ports.InsurancePort;
import App.Clinica.Ports.PatientPort;
import App.Clinica.Ports.UserPort;
import App.Clinica.converters.InsuranceConverter;
import App.Clinica.converters.PatientConverter;
import App.Clinica.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    @Autowired
    private PatientPort patientPort;

    @Autowired
    private UserPort userPort;

    @Autowired
    private InsurancePort insurancePort;

    @Autowired
    private OrderService orderService;

    public Billing generateBilling(String patientId, int doctorId) {
        // Obtener información del paciente
        Patient patient = PatientConverter.convertToModel(
            patientPort.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"))
        );

        // Obtener información del médico
        User doctor = UserConverter.convertToModel(
            userPort.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"))
        );

        // Obtener información del seguro
        Insurance insurance = InsuranceConverter.convertToModel(
            insurancePort.findByPatientId(patientId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró información de seguro para el paciente"))
        );

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