package com.nttdata.bank.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
public class Client {
    @Id
    private String id;
    private String typePerson;
    private String typeDocument;
    private String numberDocument;
    private String name;
}
