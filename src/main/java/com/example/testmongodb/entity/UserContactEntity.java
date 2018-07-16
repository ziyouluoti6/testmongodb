package com.example.testmongodb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "contacts")
public class UserContactEntity {
    @Id
    private String id;
    private String userAddr;
    private List<UserContact> contacts;
}
