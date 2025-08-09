package com.ecommercepurchase.test;

import com.ecommercepurchase.entities.Product;
import com.ecommercepurchase.record.ProductResponse;
import com.ecommercepurchase.repository.ProductRepository;
import com.ecommercepurchase.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    //TODO: @Mock ve @InkectMock arasındaki fark nedir?

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProductsInProductResponseType() {
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Macbook Pro");

        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductResponse> productResponseList = productService.getAllProductsInProductResponseType();

        assertThat(productResponseList).isNotNull();
        assertThat(productResponseList.size()).isEqualTo(1);
    }
}
