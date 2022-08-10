package com.nttdata.bank.model.service;

import com.nttdata.bank.model.document.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> findAll();

    Mono<Client> create(Client client);

    Mono<Client> update(Client client);

    Mono<Void> deleteById(String id);
}
