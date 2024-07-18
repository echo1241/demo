package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;

@Data
public class User {
    private int userId;
    private boolean isAttendance = true;
}
