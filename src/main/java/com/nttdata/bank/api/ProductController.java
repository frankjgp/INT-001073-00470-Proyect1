package com.nttdata.bank.api;

import com.nttdata.bank.model.document.Client;
import com.nttdata.bank.model.document.Product;
import com.nttdata.bank.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<Product> findAll() {
        return this.productService.findAll();
    }

    @PostMapping
    public Mono<Product> create(@RequestBody Product product) {
        return this.productService.create(product);
    }

    @PutMapping
    public Mono<Product> update(@RequestBody Product product) {
        return this.productService.update(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.productService.deleteById(id);
    }
}
