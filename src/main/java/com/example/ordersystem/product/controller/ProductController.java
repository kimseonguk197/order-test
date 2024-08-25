package com.example.ordersystem.product.controller;


import com.example.ordersystem.common.dto.CommonResDto;
import com.example.ordersystem.product.domain.Product;
import com.example.ordersystem.product.dto.ProductResDto;
import com.example.ordersystem.product.dto.ProductSaveReqDto;
import com.example.ordersystem.product.dto.ProductSearchDto;
import com.example.ordersystem.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/product/create")
    public ResponseEntity<?> productCreate(ProductSaveReqDto dto){
        Product product = productService.productAwsCreate(dto);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, "item is successfully created", product.getId());
        return new ResponseEntity<>(commonResDto, HttpStatus.CREATED);
    }
    // @GetMapping("/product/list")
    // public ResponseEntity<?> productList(ProductSearchDto searchDto, Pageable pageable){
    //     System.out.println(searchDto);
    //     Page<ProductResDto> dtos = productService.productList(searchDto, pageable);
    //     CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "정상조회완료", dtos);
    //     return new ResponseEntity<>(commonResDto, HttpStatus.OK);
    // }

    @GetMapping("/product/list")
    public String productList(ProductSearchDto searchDto, Pageable pageable){
        return "hello world2";
    }


}
