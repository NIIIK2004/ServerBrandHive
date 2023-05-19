package com.example.brandboostserver.controller;

import com.example.brandboostserver.model.Order;
import com.example.brandboostserver.response.BaseResponse;
import com.example.brandboostserver.response.OrderListResponse;
import com.example.brandboostserver.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllOrders(){
        return ResponseEntity.ok(new OrderListResponse(orderService.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addOrder(@Valid @RequestBody Order data){
        try{
            orderService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Заказ успешно добавлен!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateOrder(@Valid @RequestBody Order data){
        try{
            orderService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Заказ успешно обновлен!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteOrder(@PathVariable Long id) {
        try {
            orderService.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Заказ успешно удален!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BaseResponse> getOrderByIpAddress(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new OrderListResponse(orderService.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }
}
