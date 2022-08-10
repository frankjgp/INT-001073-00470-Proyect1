package com.nttdata.bank.api;

import com.nttdata.bank.model.document.Client;
import com.nttdata.bank.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public Flux<Client> findAll() {
        return this.clientService.findAll();
    }

    @PostMapping
    public Mono<Client> create(@RequestBody Client client) {
        return this.clientService.create(client);
    }

    @PutMapping
    public Mono<Client> update(@RequestBody Client client) {
        return this.clientService.update(client);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.clientService.deleteById(id);
    }
}
