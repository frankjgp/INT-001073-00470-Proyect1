package com.nttdata.bank.model.repository;

import com.nttdata.bank.model.document.Operation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends ReactiveMongoRepository<Operation, String> {

}
