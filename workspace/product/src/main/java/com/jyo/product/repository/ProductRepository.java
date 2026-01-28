package com.jyo.product.repository;

import com.jyo.product.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository <Product, String> {
}
