package App.Clinica.Services;

import App.Clinica.Entities.MedicineEntity;
import App.Clinica.Ports.MedicinePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {
    @Autowired
    private MedicinePort medicinePort;

    public MedicineEntity getMedicineById(String id) {
        return medicinePort.findById(id).orElse(null);
    }
} 