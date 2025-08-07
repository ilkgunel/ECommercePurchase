package com.ecommercepurchase.service;

import com.ecommercepurchase.entities.Product;
import com.ecommercepurchase.record.ProductResponse;
import com.ecommercepurchase.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable("AllProductsInProductResponseType")
    public List<ProductResponse> getAllProductsInProductResponseType() {
        List<Product> allProducts = productRepository.findAll();
        return convertProductList(allProducts);
    }

    private List<ProductResponse> convertProductList(List<Product> productList) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        productList.forEach(product -> {
            ProductResponse productResponse = new ProductResponse(product.getId(), product.getProductName());
            productResponseList.add(productResponse);
        });

        return productResponseList;
    }
}
