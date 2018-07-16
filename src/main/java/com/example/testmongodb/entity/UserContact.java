package com.example.testmongodb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserContact {
    private String firstName;
    private String lastName;
    private String receiAddr;
    private String phone;
}
