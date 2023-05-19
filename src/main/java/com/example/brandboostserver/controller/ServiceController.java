package com.example.brandboostserver.controller;

import com.example.brandboostserver.model.Services;
import com.example.brandboostserver.response.BaseResponse;
import com.example.brandboostserver.response.ServiceListResponse;
import com.example.brandboostserver.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllServices(){
        return ResponseEntity.ok(new ServiceListResponse(serviceService.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addService(@Valid @RequestBody Services data){
        try{
            serviceService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Услуга успешно добавлена!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateService(@Valid @RequestBody Services data){
        try{
            serviceService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Услуга успешно обновлена!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteService(@PathVariable Long id) {
        try {
            serviceService.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Услуга успешно удалена!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/find")
    public ResponseEntity<BaseResponse> getServiceByOption(@RequestParam(required = false, defaultValue = "") String param){
        return ResponseEntity.ok(new ServiceListResponse(serviceService.findServiceByOptions(param, param)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BaseResponse> getServiceByIpAddress(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ServiceListResponse(serviceService.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }
}
