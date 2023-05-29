package com.tosinsa.toy.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
@Data
public class ItemForm {

    private Long itemId;

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1000,max = 1000000)
    private Integer price;

    @NotNull
    @Range(min = 0, max = 9999)
    private Integer stockQuantity;

}
