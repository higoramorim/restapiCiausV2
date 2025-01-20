package com.ciaus.restapiCiaus.service;

import com.ciaus.restapiCiaus.dto.ProductDto;
import com.ciaus.restapiCiaus.exception.ProductNotFoundException;
import com.ciaus.restapiCiaus.mapper.ProductMapper;
import com.ciaus.restapiCiaus.model.Product;
import com.ciaus.restapiCiaus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(Product product){
        Product newProduto = productRepository.save(product);
        return ProductMapper.mapToDto(newProduto);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public ProductDto getProductById(int id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("produto inexistene")
        );
        return ProductMapper.mapToDto(product);
    }

    public ProductDto updateProduct(ProductDto productDto, int id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("produto inexistente")
        );
        product.setName(productDto.getName());
        Product updated = productRepository.save(product);

        return ProductMapper.mapToDto(updated);
    }

    public void deleteProduct(int id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("produto inexistente")
        );
        productRepository.delete(product);
    }
}
