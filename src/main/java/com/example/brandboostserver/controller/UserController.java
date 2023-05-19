package com.example.brandboostserver.controller;

import com.example.brandboostserver.model.User;
import com.example.brandboostserver.response.BaseResponse;
import com.example.brandboostserver.response.UserListResponse;
import com.example.brandboostserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllUsers(){
        return ResponseEntity.ok(new UserListResponse(userService.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addUser(@Valid @RequestBody User data){
        try{
            userService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Пользователь успешно добавлен!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody User data){
        try{
            userService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Пользователь успешно обновлен!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Пользователь успешно удален!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/find")
    public ResponseEntity<BaseResponse> getUserByOption(@RequestParam(required = false, defaultValue = "") String param){
        return ResponseEntity.ok(new UserListResponse(userService.findByUserByOption(param)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BaseResponse> getUserByIpAddress(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new UserListResponse(userService.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }


}
