package com.petrsushilin.ifmo.payonway.controllers;

import com.petrsushilin.ifmo.payonway.entity.Order;
import com.petrsushilin.ifmo.payonway.entity.Product;
import com.petrsushilin.ifmo.payonway.entity.nums.OrderStatus;
import com.petrsushilin.ifmo.payonway.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping(path = "/add")
    public void postClient(@RequestBody Order order){
        orderService.registerOrder(order);
    }

    @GetMapping(path = "/id={orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PutMapping(path = "/change/id={orderId}/fields")
    public void updateOrderStatus(@PathVariable Long orderId,
                                  @RequestParam(required = false) Map<Long, Product> products,
                                  @RequestParam(required = false) OrderStatus orderStatus) {
        orderService.changeOrderFields(orderId, products, orderStatus);
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
}