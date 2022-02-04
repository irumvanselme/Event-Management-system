package rw.ac.rca.ems.utils.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import rw.ac.rca.ems.models.enums.EEventRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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
