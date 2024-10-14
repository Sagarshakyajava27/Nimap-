package com.nipam.repose;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nipam.entity.Product;

public interface Repose extends JpaRepository<Product, Integer> {

}
