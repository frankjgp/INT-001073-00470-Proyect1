package com.nttdata.bank.model.repository;

import com.nttdata.bank.model.document.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

}
