package com.yaksha.training.laptopstore.repository;

import com.yaksha.training.laptopstore.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    @Query(value = "Select c from Laptop c where lower(name) like %:keyword% or lower(brand) like %:keyword%")
    List<Laptop> findByLaptopNameAndBrand(@Param("keyword") String keyword);
}
