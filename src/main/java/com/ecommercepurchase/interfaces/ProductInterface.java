package com.ecommercepurchase.interfaces;

import com.ecommercepurchase.record.ProductRequest;
import com.ecommercepurchase.record.ProductResponse;

import java.util.List;

public interface ProductInterface {
    public List<ProductResponse> getAllProductsInProductResponseType();
    public ProductResponse addProduct(ProductRequest productRequest);
}
