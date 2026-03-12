package com.cs.dao;
import lombok.Data;

@Data
public class Result {
    String token;
    String code;
    Object data;
}
