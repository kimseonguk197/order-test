package com.example.ordersystem.ordering.controller;

import com.example.ordersystem.common.dto.CommonResDto;
import com.example.ordersystem.ordering.domain.Ordering;
import com.example.ordersystem.ordering.dto.OrderListResDto;
import com.example.ordersystem.ordering.dto.OrderSaveReqDto;
import com.example.ordersystem.ordering.service.OrderingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderingController {
    private final OrderingService orderingService;

    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @PostMapping("order/create")
    public ResponseEntity<?> orderCreate(@RequestBody List<OrderSaveReqDto> dto){
        Ordering ordering = orderingService.orderCreate(dto);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, "정상완료", ordering.getId());
        return new ResponseEntity<>(commonResDto , HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("order/list")
    public ResponseEntity<?> orderList(){
        List<OrderListResDto> orderList = orderingService.orderList();
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "정상조회완료", orderList);
        return new ResponseEntity<>(commonResDto , HttpStatus.OK);
    }

//    내주문만 볼수 있는 myOrders : order/myorders

    @GetMapping("order/myorders")
    public  ResponseEntity<?> myOrders(){
        List<OrderListResDto> orderList = orderingService.myOrders();
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "정상조회완료", orderList);
        return new ResponseEntity<>(commonResDto , HttpStatus.OK);
    }

//    admin사용자가 주문취소 : /order/{id}/cancel -> orderstatus만변경
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/order/{id}/cancel")
    public ResponseEntity<?> orderCancel(@PathVariable Long id){
        Ordering ordering = orderingService.orderCancel(id);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, "정상취소", ordering.getId());
        return new ResponseEntity<>(commonResDto , HttpStatus.CREATED);
    }


}
