package App.Clinica.Factories;

import App.Clinica.Entities.InsuranceEntity;
import App.Clinica.Models.Insurance;
import App.Clinica.converters.InsuranceConverter;


public class InsuranceFactory {
    public static InsuranceEntity create(Insurance insurance) {
        return InsuranceConverter.convertToEntity(insurance);
    }
} 