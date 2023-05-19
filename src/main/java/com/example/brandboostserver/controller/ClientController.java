package com.example.brandboostserver.controller;

import com.example.brandboostserver.model.Client;
import com.example.brandboostserver.response.BaseResponse;
import com.example.brandboostserver.response.ClientListResponse;
import com.example.brandboostserver.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllClients(){
        return ResponseEntity.ok(new ClientListResponse(clientService.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addClient(@Valid @RequestBody Client data){
        try{
            clientService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Клиент успешно добавлен!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateClient(@Valid @RequestBody Client data){
        try{
            clientService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Клиент успешно обновлен!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteClient(@PathVariable Long id) {
        try {
            clientService.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Клиент успешно удален!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/find")
    public ResponseEntity<BaseResponse> getClientByOption(@RequestParam(required = false, defaultValue = "") String param){
        return ResponseEntity.ok(new ClientListResponse(clientService.findClientByOptions(param, param, param, param, param, param)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BaseResponse> getClientByIpAddress(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ClientListResponse(clientService.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }
}
