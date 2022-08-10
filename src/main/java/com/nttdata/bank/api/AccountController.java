package com.nttdata.bank.api;

import com.nttdata.bank.model.document.Account;
import com.nttdata.bank.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public Flux<Account> findAll() {
        return this.accountService.findAll();
    }

    @GetMapping("/client/{id}")
    public Flux<Account> findByClient(@PathVariable String id) {
        return this.accountService.findByClient(id);
    }

    @PostMapping
    public Mono<Account> create(@RequestBody Account account) {
        return this.accountService.create(account);
    }

    @PutMapping
    public Mono<Account> update(@RequestBody Account account) {
        return this.accountService.update(account);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.accountService.deleteById(id);
    }
}
