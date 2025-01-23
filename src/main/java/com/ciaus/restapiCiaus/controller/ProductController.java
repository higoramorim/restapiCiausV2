package com.ciaus.restapiCiaus.controller;

import com.ciaus.restapiCiaus.dto.ProductDto;
import com.ciaus.restapiCiaus.exception.ProductNotFoundException;
import com.ciaus.restapiCiaus.model.Product;
import com.ciaus.restapiCiaus.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable int id){
        try {
            return new ResponseEntity<>(
                    productService.getProductById(id), HttpStatus.OK
            );

        } catch (Exception e) {
            throw new ProductNotFoundException("produto inexistente na base de dados");
        }
    }

    @PostMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody Product product
    ){
        return new ResponseEntity<>(
                productService.createProduct(product), HttpStatus.CREATED
        );
    }

    @PutMapping("products")
    public ResponseEntity<ProductDto> updateProduct(
            @RequestBody ProductDto productDto,
            @RequestParam int id
    ){
        ProductDto response = productService.updateProduct(productDto, id);
        return new ResponseEntity<>(
                response, HttpStatus.OK
        );
    }

    @DeleteMapping("products")
    public ResponseEntity<String> deleteProduct(@RequestParam int id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Produto excluído", HttpStatus.OK);
    }
}
