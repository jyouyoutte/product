package com.jyo.product.controller;

import com.jyo.product.entity.Product;
import com.jyo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository productRepository;

    // GET /api/v1/products - list all products
    @GetMapping
    public Flux<Product> getAllProducts() {
        LOGGER.info("GET all products");
        return productRepository.findAll();
    }

    // GET /api/v1/products/{id} - get product by id
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable String id) {
        LOGGER.info("GET product by id={}", id);
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // POST /api/v1/products - create a new product
    @PostMapping
    public Mono<ResponseEntity<Product>> createProduct(@RequestBody Product product, UriComponentsBuilder uriBuilder) {
        LOGGER.info("POST create product name={}", product != null ? product.getName() : null);
        // Ensure we create a new product
        product.setId(null);
        return productRepository.save(product)
                .map(saved -> {
                    var uri = uriBuilder.path("/api/v1/products/{id}").buildAndExpand(saved.getId()).toUri();
                    LOGGER.info("Product created id={}", saved.getId());
                    return ResponseEntity.created(uri).body(saved);
                });
    }

    // PUT /api/v1/products/{id} - replace product (idempotent)
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> replaceProduct(@PathVariable String id, @RequestBody Product product) {
        LOGGER.info("PUT replace product id={}", id);
        product.setId(id);
        return productRepository.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        LOGGER.info("PUT product id={} not found", id);
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                    return productRepository.save(product).map(saved -> {
                        LOGGER.info("PUT product id={} saved", saved.getId());
                        return ResponseEntity.ok(saved);
                    });
                });
    }

    // PATCH /api/v1/products/{id} - partial update (only non-null fields are applied)
    @PatchMapping("/{id}")
    public Mono<ResponseEntity<Product>> patchProduct(@PathVariable String id, @RequestBody Product patch) {
        LOGGER.info("PATCH product id={}", id);
        return productRepository.findById(id)
                .flatMap(existing -> {
                    boolean changed = false;
                    if (patch.getName() != null && !patch.getName().equals(existing.getName())) {
                        existing.setName(patch.getName());
                        changed = true;
                    }
                    if (patch.getPrice() != null) {
                        BigDecimal existingPrice = existing.getPrice();
                        if (existingPrice == null || patch.getPrice().compareTo(existingPrice) != 0) {
                            existing.setPrice(patch.getPrice());
                            changed = true;
                        }
                    }
                    if (!changed) {
                        LOGGER.info("PATCH product id={} no changes", id);
                        return Mono.just(ResponseEntity.ok(existing));
                    }
                    return productRepository.save(existing).map(saved -> {
                        LOGGER.info("PATCH product id={} updated", saved.getId());
                        return ResponseEntity.ok(saved);
                    });
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/products/{id} - delete product
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
        LOGGER.info("DELETE product id={}", id);
        return productRepository.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        LOGGER.info("DELETE product id={} not found", id);
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                    return productRepository.deleteById(id)
                            .then(Mono.just(ResponseEntity.noContent().build()));
                });
    }

}
