package com.cs.dao;

import lombok.Data;

@Data
public class user {
    int userId;
    String userName;
    String userPassword;
    boolean isAdmin;
}
