package com.example.currencyConvert.Response;


import com.example.currencyConvert.Helper.Helper;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response {
    Double amount;

    @Override
    public String toString() {
        return Helper.toJson(this);
    }
}
