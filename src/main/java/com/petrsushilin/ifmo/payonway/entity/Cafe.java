package com.petrsushilin.ifmo.payonway.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "cafes")
public class Cafe {
    @Id
    @SequenceGenerator(
            name = "cafe_generator",
            sequenceName = "cafe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cafe_generator"
    )
    private Long id;
    private String nameOfShop;
    private String theLawAddress;
    @ElementCollection(targetClass=String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "cafe_actual_addresses", joinColumns = @JoinColumn(name = "cafe_id"))
    private List<String> theActualAddresses;
    @ElementCollection(targetClass=String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "cafe_menu", joinColumns = @JoinColumn(name = "cafe_id"))
    private List<Product> theMenu;

    public Long getId() {
        return id;
    }

    public String getNameOfShop() {
        return nameOfShop;
    }

    public void setNameOfShop(String nameOfShop) {
        this.nameOfShop = nameOfShop;
    }

    public String getTheLawAddress() {
        return theLawAddress;
    }

    public void setTheLawAddress(String theLawAddress) {
        this.theLawAddress = theLawAddress;
    }

    public List<String> getTheActualAddresses() {
        return theActualAddresses;
    }

    public void setTheActualAddresses(List<String> theActualAddresses) {
        this.theActualAddresses = theActualAddresses;
    }

    public List<Product> getTheMenu() {
        return theMenu;
    }

    public void setTheMenu(List<Product> theMenu) {
        this.theMenu = theMenu;
    }
}
