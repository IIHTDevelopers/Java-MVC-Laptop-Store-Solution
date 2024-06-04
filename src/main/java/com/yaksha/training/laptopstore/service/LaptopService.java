package com.yaksha.training.laptopstore.service;

import com.yaksha.training.laptopstore.entity.Laptop;
import com.yaksha.training.laptopstore.repository.LaptopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public Page<Laptop> getLaptops(Pageable pageable) {
        Page<Laptop> laptops = laptopRepository.findAllLaptop(pageable);
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

    public Page<Laptop> searchLaptops(String theSearchName, Pageable pageable) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return laptopRepository.findByNameOrBrandOrProcessor(theSearchName, pageable);
        } else {
            return laptopRepository.findAllLaptop(pageable);
        }
    }

    public boolean updateBestSeller(Long id) {
        laptopRepository.updateBestSeller(id);
        return true;
    }

}
