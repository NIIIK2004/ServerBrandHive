package com.example.brandboostserver.repo;

import com.example.brandboostserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
