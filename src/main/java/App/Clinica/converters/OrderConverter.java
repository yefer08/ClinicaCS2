package App.Clinica.converters;

import App.Clinica.Entities.OrderEntity;
import App.Clinica.Models.Order;

public class OrderConverter {
    public static Order convertToModel(OrderEntity entity) {
        if (entity == null) return null;
        return new Order(
            entity.getNumeroOrden(),
            entity.getPatient(),
            entity.getDoctor(),
            entity.getFecha()
        );
    }

    // Note: Since OrderEntity is abstract, we can't create instances directly
    // This method is kept for interface consistency but will throw an exception
    public static OrderEntity convertToEntity(Order model) {
        throw new UnsupportedOperationException("Cannot create abstract OrderEntity directly");
    }
} 