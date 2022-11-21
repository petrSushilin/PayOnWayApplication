package com.petrsushilin.ifmo.payonway.service;

import com.petrsushilin.ifmo.payonway.entity.Cafe;
import com.petrsushilin.ifmo.payonway.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CafeService {
    private final CafeRepository repository;

    public void registerCafe(Cafe cafe) {
        repository.save(cafe);
    }

    public Cafe getCafe(Long cafeId) {
        return repository.findById(cafeId)
                .orElseThrow(() -> new IllegalArgumentException("Cafe is not exists"));
    }

    @Transactional
    public void addActualAddresses(Long cafeId, List<String> actualAddress) {
        Cafe cafe = repository.findById(cafeId)
                        .orElseThrow(() -> new IllegalArgumentException("Cafe is not exists"));

        cafe.setTheActualAddresses(actualAddress);
    }

    @Transactional
    public void updateCafe(Long cafeId, String name, String theLawAddress) {
        Cafe cafe = repository.findById(cafeId)
                .orElseThrow(() -> new IllegalArgumentException("Cafe is not exists"));

        if (checkValidity(cafe.getNameOfShop(), name))
            cafe.setNameOfShop(name);
        if (checkValidity(cafe.getTheLawAddress(), theLawAddress))
            cafe.setTheLawAddress(theLawAddress);

        repository.save(cafe);
    }

    public void remove(Long cafeId) {
        Cafe cafe;
        if (repository.existsById(cafeId)) {
            cafe = repository.getById(cafeId);
            repository.delete(cafe);
        }
        throw new IllegalArgumentException("Cafe is not exists");
    }

    private <T> boolean checkValidity(T something, T another) {
        return (another != null && !another.equals(something));
    }

    @Autowired
    public CafeService(CafeRepository repository) {
        this.repository = repository;
    }
}
