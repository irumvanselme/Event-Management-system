package rw.ac.rca.ems.utils.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
