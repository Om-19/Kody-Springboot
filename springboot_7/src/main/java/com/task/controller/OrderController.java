package com.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.task.dto.request.OrderRequestDTO;
import com.task.dto.response.OrderResponseDTO;
import com.task.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO createOrder(

            @Valid @RequestBody OrderRequestDTO requestDTO) {

        return orderService.createOrder(requestDTO);
    }
}