package com.example.tvtcsknow.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemCreateRequest {

    @NotNull
    private Long orderId;

    @NotNull
    private Long productId;

    @NotNull
    private int quantity;

}
