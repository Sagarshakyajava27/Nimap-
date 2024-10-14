package com.nipam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.nipam.entity.Product;
import com.nipam.repose.Repose;
@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
	@Autowired
	Repose repo;
	
	@PostMapping("/nimap/save")
	public ResponseEntity<String> saveEmp(@RequestBody Product nimap) {

		try {
			repo.save(nimap);
			return ResponseEntity.ok("Data save Successfully");

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body("unable to save the data");

		}

	}
	
	@GetMapping("/viewall/all")
	public ResponseEntity<List<Product>> getAllBooks() {

		List<Product> all =repo.findAll();

		return ResponseEntity.ok(all);

	}

	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Product> deleteData(@PathVariable int id) {

		try {
			Optional<Product> prdct= repo.findById(id);
			if (prdct.isPresent()) {
				Product product = prdct.get();
				repo.deleteById(product.getId());
				return ResponseEntity.ok(product);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@PutMapping("/nimap/update/{id}")
	public ResponseEntity<Product> updateProductEntity(@PathVariable int id,@RequestBody Product prdct) {

		try {
			Optional<Product> product = repo.findById(prdct.getId());
			if (product.isPresent()) {
				repo.save(prdct);
				return ResponseEntity.ok(prdct);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/nimap/view/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		try {
			Optional<Product> product = repo.findById(id);

			if (product.isPresent()) {
				Product p=  product.get();

				return ResponseEntity.ok(p);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}

	}


	
	
	

}
