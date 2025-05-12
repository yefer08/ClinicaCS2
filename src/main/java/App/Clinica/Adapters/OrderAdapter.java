package App.Clinica.Adapters;

import App.Clinica.Entities.OrderEntity;
import App.Clinica.Ports.OrderPort;
import App.Clinica.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderAdapter implements OrderPort {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderEntity> findByPatientId(String patientId) {
        return orderRepository.findByPatientIdPatient(patientId);
    }

    @Override
    public Optional<OrderEntity> findById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return orderRepository.existsById(id);
    }
} 