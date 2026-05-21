package com.task.service;

import com.task.dto.request.OrderRequestDTO;
import com.task.dto.response.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    // Create Order
    OrderResponseDTO createOrder(
            OrderRequestDTO requestDTO);

    // Get Order By ID
    OrderResponseDTO getOrderById(
            Long orderId);

    // Get Orders By Customer Email
    List<OrderResponseDTO> getOrdersByCustomerEmail(
            String customerEmail);

    // Cancel Order
    void cancelOrder(
            Long orderId);
}