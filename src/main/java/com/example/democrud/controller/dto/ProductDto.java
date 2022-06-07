package com.example.democrud.controller.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Double price;
}
