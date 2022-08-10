package com.nttdata.bank.model.service;

import com.nttdata.bank.model.document.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationService {

    Flux<Operation> findAll();

    Flux<Operation> findByAccount(String id);

    Mono<Operation> create(Operation operation);

    Mono<Operation> update(Operation operation);

    Mono<Void> deleteById(String id);
}
