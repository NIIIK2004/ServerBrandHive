package com.example.brandboostserver.service;

import com.example.brandboostserver.model.Client;
import com.example.brandboostserver.repo.ClientRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public void save(Client client){
        clientRepo.save(client);
    }

    public List<Client> getAll(){
        return clientRepo.findAll();
    }

    public Optional<Client> getById(Long id){
        return clientRepo.findById(id);
    }

    public void delete(Long id){
        this.clientRepo.deleteById(id);
    }

    public List<Client> findClientByOptions(String name, String surname, String lastname, String number, String address, String email){
        return clientRepo.findAllByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrNumberContainingIgnoreCaseOrAddressContainingIgnoreCaseOrEmailContainingIgnoreCase(name, surname, lastname, number, address, email);
    }
}
