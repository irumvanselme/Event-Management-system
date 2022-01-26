package com.okava.pay.utils.dtos;

import com.okava.pay.models.enums.EEventRole;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateInvitationsByTagDTO {

    @NotNull
    private Long eventId;

    @NotEmpty
    private String message;

    @NotNull
    private EEventRole role;

    @NotNull
    private Long tagId;

}
