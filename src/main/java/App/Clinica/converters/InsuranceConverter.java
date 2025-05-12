package App.Clinica.converters;

import App.Clinica.Entities.InsuranceEntity;
import App.Clinica.Models.Insurance;


public class InsuranceConverter {
    
    public static Insurance convertToModel(InsuranceEntity entity) {
        if (entity == null) return null;
        
        return new Insurance(
            entity.getInsuranceCompany(),
            entity.getPolicyNumber(),
            entity.isActive(),
            entity.getExpirationDate(),
            PatientConverter.convertToModel(entity.getPatient())
        );
    }

    public static InsuranceEntity convertToEntity(Insurance model) {
        if (model == null) return null;
        
        return new InsuranceEntity(
            model.getInsuranceCompany(),
            model.getPolicyNumber(),
            model.isActive(),
            model.getExpirationDate(),
            PatientConverter.convertToEntity(model.getPatient())
        );
    }
} 