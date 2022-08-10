package com.nttdata.bank.service;

import com.nttdata.bank.model.document.Account;
import com.nttdata.bank.model.document.Client;
import com.nttdata.bank.model.repository.AccountRepository;
import com.nttdata.bank.model.repository.ClientRepository;
import com.nttdata.bank.model.service.AccountService;
import com.nttdata.bank.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Flux<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Flux<Account> findByClient(String id) {
        return this.accountRepository.findAll().filter(c -> c.getClient().getId().equals(id));
    }

    @Override
    public Mono<Account> create(Account account) {

        if (account.getProduct().getId().equals(Constant.TypeAccount.Ahorro.getId())) {
            if (account.getClient().getTypePerson().equals(Constant.TypePerson.Empresarial.getId())) {
                return Mono.error(new RuntimeException("No puede tener una cuenta de ahorro"));
            }
            return this.accountRepository.findAll()
                    .filter(f -> f.getProduct().getId().equals(account.getProduct().getId()) &&
                            f.getClient().getId().equals(account.getClient().getId()))
                    .single(account).flatMap(c -> {
                        if (c.getId() == null) {
                            return this.accountRepository.save(c);
                        } else {
                            return Mono.error(new RuntimeException("Máximo una cuenta de ahorro por persona"));
                        }
                    });
        } else if (account.getProduct().getId().equals(Constant.TypeAccount.Corriente.getId())) {
            if (account.getClient().getTypePerson().equals(Constant.TypePerson.Personal.getId())) {
                return this.accountRepository.findAll()
                        .filter(f -> f.getProduct().getId().equals(account.getProduct().getId()) &&
                                f.getClient().getId().equals(account.getClient().getId()))
                        .single(account).flatMap(c -> {
                            if (c.getId() == null) {
                                return this.accountRepository.save(c);
                            } else {
                                return Mono.error(new RuntimeException("Máximo una cuenta corriente por persona"));
                            }
                        });
            } else if ((account.getTitulars() == null ? false : (account.getTitulars().size() == 0 ? false : true)) == false) {
                return Mono.error(new RuntimeException("Requiere un titular como mínimo para esta cuenta"));
            } else {
                return this.accountRepository.save(account);
            }
        } else if (account.getProduct().getId().equals(Constant.TypeAccount.PlazoFijo.getId())) {
            if (account.getClient().getTypePerson().equals(Constant.TypePerson.Empresarial.getId())) {
                return Mono.error(new RuntimeException("No puede tener una cuenta plazo fijo"));
            } else {
                return this.accountRepository.findAll()
                        .filter(f -> f.getProduct().getId().equals(account.getProduct().getId()) &&
                                f.getClient().getId().equals(account.getClient().getId()))
                        .single(account).flatMap(c -> {
                            if (c.getId() == null) {
                                return this.accountRepository.save(c);
                            } else {
                                return Mono.error(new RuntimeException("Máximo una cuenta plazo fijo por persona"));
                            }
                        });
            }
        } else if (account.getProduct().getId().equals(Constant.TypeAccount.Personal.getId())) {
            if (account.getClient().getTypePerson().equals(Constant.TypePerson.Empresarial.getId())) {
                return Mono.error(new RuntimeException("No puede tener un crédito personal"));
            }
            return this.accountRepository.findAll()
                    .filter(f -> f.getProduct().getId().equals(account.getProduct().getId()) &&
                            f.getClient().getId().equals(account.getClient().getId()))
                    .single(account).flatMap(c -> {
                        if (c.getId() == null) {
                            return this.accountRepository.save(c);
                        } else {
                            return Mono.error(new RuntimeException("Máximo un crédito personal"));
                        }
                    });
        } else if (account.getProduct().getId().equals(Constant.TypeAccount.Empresarial.getId())) {
            if (account.getClient().getTypePerson().equals(Constant.TypePerson.Personal.getId())) {
                return Mono.error(new RuntimeException("No puede tener un crédito empresarial"));
            } else {
                return this.accountRepository.save(account);
            }
        } else {
            return this.accountRepository.save(account);
        }
    }

    @Override
    public Mono<Account> update(Account account) {
        return this.accountRepository.findById(account.getId())
                .map((c) -> {
                    c.setBalance(c.getBalance() + account.getBalance());
                    return c;
                })
                .flatMap(c -> this.accountRepository.save(c));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return this.accountRepository.deleteById(id);
    }
}
