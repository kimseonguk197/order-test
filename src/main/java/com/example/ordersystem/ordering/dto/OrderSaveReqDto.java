package com.example.ordersystem.ordering.dto;


import com.example.ordersystem.member.domain.Member;
import com.example.ordersystem.ordering.domain.OrderDetail;
import com.example.ordersystem.ordering.domain.OrderStatus;
import com.example.ordersystem.ordering.domain.Ordering;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSaveReqDto {
        private Long productId;
        private Integer productCount;
//    private Long memberId;
//    private List<OrderDto> orderDtos;

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class OrderDto{
//        private Long productId;
//        private Integer productCount;
//    }

    public Ordering toEntity(Member member){
        return Ordering.builder()
                .member(member)
                .build();
    }
}
