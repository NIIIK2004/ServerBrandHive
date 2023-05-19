package com.example.brandboostserver.response;

import com.example.brandboostserver.model.Services;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceListResponse extends BaseResponse{
    private List<Services> data;
    public ServiceListResponse(List<Services> data){
        super(true, "Услуги");
        this.data = data;
    }

    public ServiceListResponse(Optional<Services> data){
        super(true,"Услуга");
        this.data = data.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}
