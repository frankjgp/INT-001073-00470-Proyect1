package com.nttdata.bank.service;

import com.nttdata.bank.model.document.Product;
import com.nttdata.bank.model.repository.ProductRepository;
import com.nttdata.bank.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Mono<Product> create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Mono<Product> update(Product product) {
        return this.productRepository.findById(product.getId())
                .map((c) -> {
                    c.setTypeAccount(product.getTypeAccount());
                    c.setName(product.getName());
                    c.setCommissionMaintenance(product.getCommissionMaintenance());
                    c.setLimitOperation(product.getLimitOperation());
                    return c;
                })
                .flatMap(c -> this.productRepository.save(c));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return this.productRepository.deleteById(id);
    }
}
