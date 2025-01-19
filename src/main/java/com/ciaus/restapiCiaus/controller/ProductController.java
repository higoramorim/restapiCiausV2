package com.ciaus.restapiCiaus.controller;

import com.ciaus.restapiCiaus.dto.ProductDto;
import com.ciaus.restapiCiaus.model.Product;
import com.ciaus.restapiCiaus.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable int id){
        return new ResponseEntity<>(
                productService.getProductById(id), HttpStatus.OK
        );
    }

    @PostMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> create(
            @RequestBody Product product
    ){
        return new ResponseEntity<>(
                productService.create(product), HttpStatus.CREATED
        );
    }

    @PutMapping("products")
    public ResponseEntity<ProductDto> updateProduct(
            @RequestBody ProductDto productDto,
            @PathVariable int id
    ){
        ProductDto response = productService.updateProduct(productDto, id);
        return new ResponseEntity<>(
                response, HttpStatus.OK
        );
    }

    @DeleteMapping("products")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Produto excluído", HttpStatus.OK);
    }
}
