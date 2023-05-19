package com.example.brandboostserver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Укажите клиента!")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @NotNull(message = "Укажите услугу!")
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate finalDate;

    @PrePersist
    private void setOrderDate(){
        this.orderDate = LocalDate.now();
    }

}
