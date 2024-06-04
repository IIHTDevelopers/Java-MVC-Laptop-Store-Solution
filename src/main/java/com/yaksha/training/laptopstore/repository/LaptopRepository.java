package com.yaksha.training.laptopstore.repository;

import com.yaksha.training.laptopstore.entity.Laptop;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    @Query("SELECT l FROM Laptop l")
    Page<Laptop> findAllLaptop(Pageable pageable);

    @Query(value = "Select l from Laptop l where lower(name) like %:keyword% " +
            "or lower(brand) like %:keyword% " +
            "or lower(processor) like %:keyword%")
    Page<Laptop> findByNameOrBrandOrProcessor(@Param("keyword") String keyword, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE laptop SET best_seller = 1 where id = :id", nativeQuery = true)
    void updateBestSeller(@Param("id") Long id);

}
