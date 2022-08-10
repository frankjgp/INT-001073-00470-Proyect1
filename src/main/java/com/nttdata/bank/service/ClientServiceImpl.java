package com.nttdata.bank.service;

import com.nttdata.bank.model.document.Client;
import com.nttdata.bank.model.repository.ClientRepository;
import com.nttdata.bank.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Flux<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Mono<Client> create(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Mono<Client> update(Client client) {
        return this.clientRepository.findById(client.getId())
                .map((c) -> {
                    c.setTypePerson(client.getTypePerson());
                    c.setTypeDocument(client.getTypeDocument());
                    c.setNumberDocument(client.getNumberDocument());
                    c.setName(client.getName());
                    return c;
                })
                .flatMap(c -> this.clientRepository.save(c));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return this.clientRepository.deleteById(id);
    }
}
