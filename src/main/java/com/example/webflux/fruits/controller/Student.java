package com.example.webflux.fruits.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Student {

    @NotNull
    public long id;
}
