package com.reactiveprogramming.bookstore.config;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Discount {
    private String promocode;
    private Integer discount;
    private LocalDate expireDate;

}
