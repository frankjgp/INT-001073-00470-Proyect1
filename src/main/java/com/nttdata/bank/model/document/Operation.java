package com.nttdata.bank.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "operations")
public class Operation {

    @Id
    private String id;
    private Account account;
    private LocalTime date;
    private String operation;
    private Double amount;
}
