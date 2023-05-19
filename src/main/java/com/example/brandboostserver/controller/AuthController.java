package com.example.brandboostserver.controller;


import com.example.brandboostserver.model.Role;
import com.example.brandboostserver.model.User;
import com.example.brandboostserver.response.BaseResponse;
import com.example.brandboostserver.response.RoleResponse;
import com.example.brandboostserver.service.CustomUserDetailsService;
import com.example.brandboostserver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/registration")
    public ResponseEntity<BaseResponse> registration(@RequestBody User data) {
        try {
            userService.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Пользователь успешно зарегистрирован!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<RoleResponse> login(HttpServletRequest request, @RequestBody User data){
        User user = userService.findUserByUsername(data.getUsername());
        if (user != null && data.getPassword().equals(user.getPassword())){
            Set<Role> roles = user.getRoles();
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(data.getUsername());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            return ResponseEntity.ok(new RoleResponse(true, "Вы успешно авторизовались!", roles.toString()));
        }
        else{
            return ResponseEntity.badRequest().body(new RoleResponse(false,"Не правильный логин или пароль", ""));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(new BaseResponse(true, "Вы успешно вышли из системы!"));
    }


    @GetMapping("/user")
    public ResponseEntity<BaseResponse> getUserInfo(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findUserByUsername(username);
            if (user != null) {
                Set<Role> roles = user.getRoles();
                return ResponseEntity.ok(new BaseResponse(true, "Роль пользователя: " + roles));
            }
        }
        return ResponseEntity.badRequest().body(new BaseResponse(false, "Не удалось получить информацию о пользователе"));
    }
}
