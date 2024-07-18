package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "room")
public class Room {
    @Id
    private ObjectId _id;
    private String title;
    private int postId;
    private boolean isSold = false;
    private User[] users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
