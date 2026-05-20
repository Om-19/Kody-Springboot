package com.task.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.request.Productrequest;
import com.task.dto.response.Productresponse;
import com.task.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/hello")
    public String testApi() {
        return "Welcome to Product API!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Productresponse createProduct(
            @Valid @RequestBody Productrequest requestDTO) {

        return productService.createProduct(requestDTO);
    }

    /*
     * Also we can create Paginated Response Dto rather than raw Page, bcs it
     * includes meta data too
     * ex. PagedResponse<T>
     */
    // USE : http://localhost:8080/products?page=1&size=2
    @GetMapping
    public Page<Productresponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {
        return productService.getAllProducts(page, size);
    }

    @GetMapping("/sorted")
    public Page<Productresponse> getAllWithSortingAndDirection(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return productService.getAllProductsWithSortingAndDirection(page, size, sortBy, direction);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // SUCCESS - 204 NO CONTENT
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
