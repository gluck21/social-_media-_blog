package com.example.media.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseBody<T> {
    private String message;
    private T data;

    public ResponseBody(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
