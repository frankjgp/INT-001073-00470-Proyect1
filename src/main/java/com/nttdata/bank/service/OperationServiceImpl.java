package com.nttdata.bank.service;

import com.nttdata.bank.model.document.Operation;
import com.nttdata.bank.model.document.Product;
import com.nttdata.bank.model.repository.OperationRepository;
import com.nttdata.bank.model.repository.ProductRepository;
import com.nttdata.bank.model.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Flux<Operation> findAll() {
        return this.operationRepository.findAll();
    }

    @Override
    public Flux<Operation> findByAccount(String id) {
        return this.operationRepository.findAll().filter(c -> c.getAccount().getId().equals(id));
    }

    @Override
    public Mono<Operation> create(Operation operation) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        operation.setDate(timestamp.toInstant());
        return this.operationRepository.save(operation);
    }

    @Override
    public Mono<Operation> update(Operation operation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        throw new UnsupportedOperationException();
    }
}
