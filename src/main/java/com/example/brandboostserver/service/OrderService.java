package com.example.brandboostserver.service;

import com.example.brandboostserver.model.Order;
import com.example.brandboostserver.repo.OrderRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public void save(Order order){
        orderRepo.save(order);
    }

    public List<Order> getAll(){
        return orderRepo.findAll();
    }

    public Optional<Order> getById(Long id){
        return orderRepo.findById(id);
    }

    public void delete(Long id){
        this.orderRepo.deleteById(id);
    }
}
