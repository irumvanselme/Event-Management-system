package rw.ac.rca.ems.utils.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import rw.ac.rca.ems.utils.security.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegisterDTO {

    @NotEmpty
    private String fullNames;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @ValidPassword
    private String password;
}
