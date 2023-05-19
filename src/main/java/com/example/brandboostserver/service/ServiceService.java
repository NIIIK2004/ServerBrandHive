package com.example.brandboostserver.service;

import com.example.brandboostserver.model.Services;
import com.example.brandboostserver.repo.ServiceRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    private final ServiceRepo serviceRepo;

    public ServiceService(ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    public void save(Services service){
        serviceRepo.save(service);
    }

    public List<Services> getAll(){
        return serviceRepo.findAll();
    }

    public Optional<Services> getById(Long id){
        return serviceRepo.findById(id);
    }

    public void delete(Long id){
        this.serviceRepo.deleteById(id);
    }

    public List<Services> findServiceByOptions(String name, String description){
        return serviceRepo.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(name, description);
    }
}
