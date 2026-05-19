package com.task.service;

import org.springframework.data.domain.Page;

import com.task.dto.request.Productrequest;
import com.task.dto.response.Productresponse;

public interface ProductService {

    Productresponse createProduct(Productrequest product);

    Productresponse getProductById(Long id);

    Page<Productresponse> getAllProducts(
            int page,
            int size);

    void deleteProduct(Long id);

    Page<Productresponse> getAllProductsWithSortingAndDirection(int page, int size, String sortBy, String direction);
}
