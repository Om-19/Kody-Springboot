package com.task.service.impl;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.task.dto.request.Productrequest;
import com.task.dto.response.Productresponse;
import com.task.entity.Product;
import com.task.exception.customExc.DuplicateResourceException;
import com.task.exception.customExc.ProductAlreadyDeletedException;
import com.task.exception.customExc.ResourceNotFound;
import com.task.repository.ProductRepository;
import com.task.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Productresponse createProduct(Productrequest requestDto) {

        if (productRepository.existsByProductCode(requestDto.getProductCode())) {
            throw new DuplicateResourceException("Product Code Already Exists.");
        }

        // Normalize before saving.
        String normalizedCode = requestDto.getProductCode()
                .trim()
                .toUpperCase();

        Product newProduct = Product.builder()
                .productCode(normalizedCode)
                .productName(requestDto.getProductName())
                .brandName(requestDto.getBrandName())
                .price(BigDecimal.valueOf(requestDto.getPrice()))
                .stockQuantity(requestDto.getStockQuantity())
                .productDescription(requestDto.getProductDescription())
                .productImage(requestDto.getProductImage())
                .active(true)
                .build();

        // Save the product to the database
        Product savedproduct = productRepository.save(newProduct);

        return mapToResponse(savedproduct);
    }

    @Override
    public Productresponse getProductById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public Page<Productresponse> getAllProducts(
            int page,
            int size) {

        // return productRepository.findAll().stream()
        // .map(this::mapToResponse)
        // .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.map(this::mapToResponse);
    }

    @Override
    public Page<Productresponse> getAllProductsWithSortingAndDirection(int page, int size, String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.map(this::mapToResponse);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("Product with id : " + id + " not found."));

        // avoids NullPointerException
        if (Boolean.FALSE.equals(product.getActive())) {
            throw new ProductAlreadyDeletedException("Product is already deleted.");
        }

        product.setActive(false);

        productRepository.save(product);
    }

    private Productresponse mapToResponse(Product product) {

        return Productresponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .brandName(product.getBrandName())
                .price(product.getPrice().doubleValue())
                .stockQuantity(product.getStockQuantity())
                .productDescription(product.getProductDescription())
                .productImage(product.getProductImage())
                .active(product.getActive())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .build();
    }

}
