package com.example.userMongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collection = "UserCollec" )
public class UserModel {

    @Id
    private Integer userId;
    private String username;
    private String email;

}
