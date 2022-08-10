package com.nttdata.bank.model.service;

import com.nttdata.bank.model.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> findAll();

    Mono<Product> create(Product product);

    Mono<Product> update(Product product);

    Mono<Void> deleteById(String id);
}
