package com.example.brandboostserver.response;

import com.example.brandboostserver.model.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderListResponse extends BaseResponse{
    private List<Order> data;
    public OrderListResponse(List<Order> data){
        super(true, "Заказы");
        this.data = data;
    }

    public OrderListResponse(Optional<Order> data){
        super(true,"Заказ");
        this.data = data.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}
