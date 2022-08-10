package com.nttdata.bank.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;
    private Client client;
    private Product product;
    private String number;
    private Double limit;
    private Double balance;
    private List<String> titulars;
    private List<String> authorizers;
}
