package com.okava.pay.utils.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateOrUpdateEventDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    private LocalDateTime time;

    @NotNull
    private Integer durationInHours;

    @NotEmpty
    private String bannerImage;
}
