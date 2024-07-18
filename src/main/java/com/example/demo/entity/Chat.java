package com.example.demo.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "chat")
public class Chat {
    @Id
    private ObjectId id;
    private int senderId;
    private ObjectId roomId;
    private String msg;
    private boolean isRead = false;
    private LocalDateTime createdAt;
}
