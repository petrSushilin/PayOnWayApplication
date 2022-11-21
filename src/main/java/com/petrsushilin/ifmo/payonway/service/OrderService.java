package com.petrsushilin.ifmo.payonway.service;

import com.petrsushilin.ifmo.payonway.entity.Order;
import com.petrsushilin.ifmo.payonway.entity.Product;
import com.petrsushilin.ifmo.payonway.entity.nums.OrderStatus;
import com.petrsushilin.ifmo.payonway.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository repository;

    public void registerOrder(Order order) {
        repository.save(order);
    }

    public Order getOrder(Long orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order is not exists"));
    }

    @Transactional
    public void changeOrderFields(Long orderId, Map<Long, Product> orderProducts, OrderStatus orderStatus) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order is not exists"));

        if(checkValidity(order.getOrderStatus(), orderStatus))
            order.setOrderStatus(orderStatus);
        if(checkValidity(order.getOrderProducts(), orderProducts))
            order.setOrderProducts(orderProducts);

        repository.save(order);
    }

    private <T> boolean checkValidity(T something, T another) {
        return (another != null && !another.equals(something));
    }

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
}
