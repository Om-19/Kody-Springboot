package com.task.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.dto.request.OrderItemRequestDTO;
import com.task.dto.request.OrderRequestDTO;
import com.task.dto.response.OrderItemResponseDTO;
import com.task.dto.response.OrderResponseDTO;
import com.task.entity.CustomerOrder;
import com.task.entity.Offer;
import com.task.entity.OfferUsage;
import com.task.entity.Product;
import com.task.entity.ProductOrder;
import com.task.enums.OfferType;
import com.task.exception.customExc.InvalidOrderException;
import com.task.exception.customExc.ResourceNotFound;
import com.task.repository.CustomerOrderRepository;
import com.task.repository.OfferRepository;
import com.task.repository.OfferUsageRepository;
import com.task.repository.ProductOrderRepository;
import com.task.repository.ProductRepository;
import com.task.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;

    private final OfferRepository offerRepository;

    private final CustomerOrderRepository customerOrderRepository;

    private final ProductOrderRepository productOrderRepository;

    private final OfferUsageRepository offerUsageRepository;

    @Override
    @Transactional
    public OrderResponseDTO createOrder(
            OrderRequestDTO requestDTO) {

        BigDecimal totalAmount = BigDecimal.ZERO;

        BigDecimal discountAmount = BigDecimal.ZERO;

        BigDecimal finalAmount;

        Offer appliedOffer = null;

        // =========================================
        // PRODUCT VALIDATION + TOTAL CALCULATION
        // =========================================

        for (OrderItemRequestDTO item : requestDTO.getProducts()) {

            Product product = productRepository
                    .findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFound(
                            "Product not found with ID: "
                                    + item.getProductId()));

            // Product inactive/deleted validation
            if (Boolean.FALSE.equals(
                    product.getActive())) {

                throw new InvalidOrderException(
                        "Product with ID "
                                + product.getProductId()
                                + " is inactive");
            }

            // Stock validation
            if (product.getStockQuantity() < item.getQuantity()) {

                throw new InvalidOrderException(
                        "Insufficient stock for product ID: "
                                + product.getProductId());
            }

            BigDecimal itemTotal = product.getPrice()
                    .multiply(
                            BigDecimal.valueOf(
                                    item.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);
        }

        // =========================================
        // OFFER VALIDATION + DISCOUNT CALCULATION
        // =========================================

        if (requestDTO.getOfferId() != null) {

            appliedOffer = offerRepository
                    .findById(requestDTO.getOfferId())
                    .orElseThrow(() -> new ResourceNotFound(
                            "Offer not found with ID: "
                                    + requestDTO.getOfferId()));

            // Offer active validation
            if (Boolean.FALSE.equals(
                    appliedOffer.getActive())) {

                throw new InvalidOrderException(
                        "Offer is inactive");
            }

            LocalDateTime now = LocalDateTime.now();

            // Offer start validation
            if (now.isBefore(
                    appliedOffer.getStartDate())) {

                throw new InvalidOrderException(
                        "Offer has not started yet");
            }

            // Offer expiry validation
            if (now.isAfter(
                    appliedOffer.getEndDate())) {

                throw new InvalidOrderException(
                        "Offer has expired");
            }

            // =========================================
            // OFFER USAGE VALIDATION
            // =========================================

            OfferUsage offerUsage = offerUsageRepository
                    .findByCustomerEmailAndOffer_offerId(
                            requestDTO.getCustomerEmail(),
                            appliedOffer.getOfferId())
                    .orElse(null);

            int usedCount = offerUsage != null
                    ? offerUsage.getUsedCount()
                    : 0;

            if (usedCount >= appliedOffer.getMaxUsagePerCustomer()) {

                throw new InvalidOrderException(
                        "Offer usage limit exceeded");
            }

            // =========================================
            // DISCOUNT CALCULATION
            // =========================================

            if (appliedOffer.getOfferType() == OfferType.FIXED) {

                discountAmount = appliedOffer.getOfferValue();
            }

            else if (appliedOffer.getOfferType() == OfferType.PERCENTAGE) {

                discountAmount = totalAmount.multiply(
                        appliedOffer.getOfferValue()
                                .divide(
                                        BigDecimal.valueOf(100)));

                // Max discount cap
                if (discountAmount.compareTo(
                        appliedOffer.getMaxDiscountValue()) > 0) {

                    discountAmount = appliedOffer.getMaxDiscountValue();
                }
            }
        }

        // =========================================
        // FINAL AMOUNT CALCULATION
        // =========================================

        finalAmount = totalAmount.subtract(discountAmount);

        // Prevent negative amount
        if (finalAmount.compareTo(
                BigDecimal.ZERO) < 0) {

            finalAmount = BigDecimal.ZERO;
        }

        // =========================================
        // SAVE CUSTOMER ORDER
        // =========================================

        CustomerOrder customerOrder = CustomerOrder.builder()
                .customerName(
                        requestDTO.getCustomerName())
                .customerEmail(
                        requestDTO.getCustomerEmail())
                .customerPhone(
                        requestDTO.getCustomerPhone())
                .shippingAddress(
                        requestDTO.getShippingAddress())
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .finalAmount(finalAmount)
                .offer(appliedOffer)
                .build();

        CustomerOrder savedOrder = customerOrderRepository
                .save(customerOrder);

        // =========================================
        // CREATE PRODUCT ORDERS
        // =========================================

        List<ProductOrder> productOrders = new ArrayList<>();

        for (OrderItemRequestDTO item : requestDTO.getProducts()) {

            Product product = productRepository
                    .findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFound(
                            "Product not found with ID: "
                                    + item.getProductId()));

            BigDecimal itemTotal = product.getPrice()
                    .multiply(
                            BigDecimal.valueOf(
                                    item.getQuantity()));

            ProductOrder productOrder = ProductOrder.builder()
                    .customerOrder(savedOrder)
                    .product(product)
                    .quantity(item.getQuantity())
                    .unitPrice(product.getPrice())
                    .totalPrice(itemTotal)

                    // Discount applies at ORDER level
                    .finalPrice(itemTotal)

                    .build();

            productOrders.add(productOrder);

            // =========================================
            // REDUCE STOCK
            // =========================================

            product.setStockQuantity(
                    product.getStockQuantity()
                            - item.getQuantity());

            productRepository.save(product);
        }

        // Save all product order items
        productOrderRepository.saveAll(productOrders);

        // =========================================
        // SAVE / UPDATE OFFER USAGE
        // =========================================

        if (appliedOffer != null) {

            OfferUsage offerUsage = offerUsageRepository
                    .findByCustomerEmailAndOffer_offerId(
                            requestDTO.getCustomerEmail(),
                            appliedOffer.getOfferId())
                    .orElse(null);

            // First time usage
            if (offerUsage == null) {

                offerUsage = OfferUsage.builder()
                        .customerEmail(
                                requestDTO.getCustomerEmail())
                        .offer(appliedOffer)
                        .usedCount(1)
                        .build();
            }

            // Existing usage
            else {

                offerUsage.setUsedCount(
                        offerUsage.getUsedCount() + 1);
            }

            offerUsageRepository.save(offerUsage);
        }

        // =========================================
        // RETURN RESPONSE
        // =========================================

        return mapToOrderResponse(
                savedOrder,
                productOrders);
    }

    // =========================================
    // RESPONSE MAPPER
    // =========================================

    private OrderResponseDTO mapToOrderResponse(

            CustomerOrder order,

            List<ProductOrder> productOrders) {

        List<OrderItemResponseDTO> items = productOrders.stream()
                .map(item -> OrderItemResponseDTO
                        .builder()
                        .productOrderId(
                                item.getProductOrderId())
                        .productId(
                                item.getProduct()
                                        .getProductId())
                        .productName(
                                item.getProduct()
                                        .getProductName())
                        .productCode(
                                item.getProduct()
                                        .getProductCode())
                        .quantity(
                                item.getQuantity())
                        .unitPrice(
                                item.getUnitPrice())
                        .totalPrice(
                                item.getTotalPrice())
                        .finalPrice(
                                item.getFinalPrice())
                        .build())
                .toList();

        return OrderResponseDTO.builder()

                .orderId(order.getOrderId())

                .customerName(order.getCustomerName())

                .customerEmail(order.getCustomerEmail())

                .customerPhone(order.getCustomerPhone())

                .shippingAddress(order.getShippingAddress())

                .orderDate(order.getOrderDate())

                .orderStatus(order.getOrderStatus())

                .paymentStatus(order.getPaymentStatus())

                .totalAmount(order.getTotalAmount())

                .discountAmount(order.getDiscountAmount())

                .finalAmount(order.getFinalAmount())

                .offerId(
                        order.getOffer() != null
                                ? order.getOffer().getOfferId()
                                : null)

                .offerName(
                        order.getOffer() != null
                                ? order.getOffer().getOfferName()
                                : null)

                .products(items)

                .build();
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        throw new UnsupportedOperationException(
                "Unimplemented method 'getOrderById'");
    }

    @Override
    public List<OrderResponseDTO> getOrdersByCustomerEmail(
            String customerEmail) {

        throw new UnsupportedOperationException(
                "Unimplemented method 'getOrdersByCustomerEmail'");
    }

    @Override
    public void cancelOrder(Long orderId) {

        throw new UnsupportedOperationException(
                "Unimplemented method 'cancelOrder'");
    }
}