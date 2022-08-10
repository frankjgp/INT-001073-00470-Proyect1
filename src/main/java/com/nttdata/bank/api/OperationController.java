package com.nttdata.bank.api;

import com.nttdata.bank.model.document.Operation;
import com.nttdata.bank.model.document.Product;
import com.nttdata.bank.model.service.OperationService;
import com.nttdata.bank.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @GetMapping
    public Flux<Operation> findAll() {
        return this.operationService.findAll();
    }

    @GetMapping("/account/{id}")
    public Flux<Operation> findByAccount(@PathVariable String id) {
        return this.operationService.findByAccount(id);
    }

    @PostMapping
    public Mono<Operation> create(@RequestBody Operation operation) {
        return this.operationService.create(operation);
    }

    @PutMapping
    public Mono<Operation> update(@RequestBody Operation operation) {
        return this.operationService.update(operation);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.operationService.deleteById(id);
    }
}
