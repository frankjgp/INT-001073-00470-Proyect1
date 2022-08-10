package com.nttdata.bank.model.service;

import com.nttdata.bank.model.document.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<Account> findAll();

    Flux<Account> findByClient(String id);

    Mono<Account> create(Account account);

    Mono<Account> update(Account account);

    Mono<Void> deleteById(String id);
}
