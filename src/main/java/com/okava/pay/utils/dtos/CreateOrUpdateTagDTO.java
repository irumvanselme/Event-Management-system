package com.okava.pay.utils.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateOrUpdateTagDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;
}
