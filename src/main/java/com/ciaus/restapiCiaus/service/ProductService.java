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

    @Autowired
    private ProductRepository productRepository;

    public ProductDto createProduct(Product product){
        Product newProduto = productRepository.save(product);
        return ProductMapper.mapToDto(newProduto);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public ProductDto getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("produto inexistene")
        );
        return ProductMapper.mapToDto(product);
    }

    public ProductDto updateProduct(ProductDto productDto, Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("produto inexistente")
        );
        product.setName(productDto.getName());
        Product updated = productRepository.save(product);

        return ProductMapper.mapToDto(updated);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("produto inexistente")
        );
        productRepository.delete(product);
    }
}
