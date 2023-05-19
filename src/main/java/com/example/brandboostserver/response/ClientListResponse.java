package com.example.brandboostserver.response;

import com.example.brandboostserver.model.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientListResponse extends BaseResponse{
    private List<Client> data;
    public ClientListResponse(List<Client> data){
        super(true, "Клиенты");
        this.data = data;
    }

    public ClientListResponse(Optional<Client> data){
        super(true,"Клиент");
        this.data = data.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}
