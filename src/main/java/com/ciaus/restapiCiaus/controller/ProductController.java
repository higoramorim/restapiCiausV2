package com.ciaus.restapiCiaus.controller;

import com.ciaus.restapiCiaus.dto.ProductDto;
import com.ciaus.restapiCiaus.model.Product;
import com.ciaus.restapiCiaus.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/")
public class ProductController {
    private static final Logger log = Logger.getLogger("1L");
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
            log.info("requesting product by id");
            return new ResponseEntity<>(

                    productService.getProductById(id), HttpStatus.OK
            );

        } catch (Exception e) {
            log.info("error get by id");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
