package com.yaksha.training.laptopstore.service;

import com.yaksha.training.laptopstore.entity.Laptop;
import com.yaksha.training.laptopstore.repository.LaptopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public List<Laptop> getLaptops() {
        List<Laptop> laptops = laptopRepository.findAll();
        return laptops;
    }

    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public Laptop getLaptop(Long id) {
        return laptopRepository.findById(id).get();
    }

    public boolean deleteLaptop(Long id) {
        laptopRepository.deleteById(id);
        return true;
    }

    public List<Laptop> searchLaptops(String theSearchName) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return laptopRepository.findByLaptopNameAndBrand(theSearchName);
        } else {
            return laptopRepository.findAll();
        }
    }
}
