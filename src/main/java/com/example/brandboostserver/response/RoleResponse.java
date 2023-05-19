package com.example.brandboostserver.response;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class RoleResponse {
    protected boolean success;
    protected String message;
    protected String role;
}
