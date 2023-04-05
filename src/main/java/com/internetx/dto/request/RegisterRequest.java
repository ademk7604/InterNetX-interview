package com.internetx.dto.request;

import com.internetx.domain.Role;
import com.internetx.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequest  extends User {
    @NotBlank

    private String login;
    @NotBlank
    private String password;
    @NotBlank

    private String firstName;
    @NotBlank

    private String lastName;

    @NotBlank
    @Size (min = 7,max = 30,message = "Please provide a valid email")
    private String email;

    @NotBlank
    private Role role;

}
