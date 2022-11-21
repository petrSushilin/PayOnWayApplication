package com.petrsushilin.ifmo.payonway.service;

import com.petrsushilin.ifmo.payonway.entity.Product;
import com.petrsushilin.ifmo.payonway.entity.nums.ProductType;
import com.petrsushilin.ifmo.payonway.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public void addProduct(Product product) {
        repository.save(product);
    }

    public Product getProduct(Long productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product is not exists"));
    }

    @Transactional
    public void changeProductFields(Long productId, String name, ProductType type, double cost) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product is not exists"));

        if (checkValidity(product.getName(), name))
            product.setName(name);
        if (checkValidity(product.getCost(), cost))
            product.setCost(cost);
        if (checkValidity(product.getType(), type))
            product.setType(type);

        repository.save(product);
    }

    private <T> boolean checkValidity(T something, T another) {
        return (another != null && !another.equals(something));
    }

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

}
