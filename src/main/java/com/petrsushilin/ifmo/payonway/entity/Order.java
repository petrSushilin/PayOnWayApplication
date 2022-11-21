package com.petrsushilin.ifmo.payonway.entity;

import com.petrsushilin.ifmo.payonway.entity.nums.OrderStatus;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Map;

@Component
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_generator",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_generator"
    )
    private Long id;
    private String numberOfOrder;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;
    @ElementCollection(targetClass=Product.class, fetch = FetchType.LAZY)
    private Map<Long, Product> orderProducts;

    @Enumerated
    private OrderStatus orderStatus;

    public String getNumberOfOrder() {
        return numberOfOrder;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public Map<Long, Product> getOrderProducts() {
        return orderProducts;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderProducts(Map<Long, Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void setOrderStatus(OrderStatus orderStatusInfo) {
        this.orderStatus = orderStatusInfo;
    }
}
