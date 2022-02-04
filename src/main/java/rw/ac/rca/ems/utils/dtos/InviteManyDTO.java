package rw.ac.rca.ems.utils.dtos;

import lombok.Data;
import rw.ac.rca.ems.models.enums.EEventRole;

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
