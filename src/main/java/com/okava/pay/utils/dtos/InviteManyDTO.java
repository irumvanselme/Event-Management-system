package com.okava.pay.utils.dtos;

import com.okava.pay.models.enums.EEventRole;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class InviteManyDTO {

    @NotNull
    private Long eventId;

    @NotEmpty
    private String message;

    @NotNull
    private EEventRole role;

    @NotEmpty
    private List<Long> userIds;
}
