package com.internetx.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Size(max = 50)
    @NotBlank(message = "Please provide your first name")
    private String firstName;
    @Size(max = 50)
    @NotBlank(message = "Please provide your last name")
    private String lastName;
    @Email(message = "Please provide a valid email")
    @Size(min = 5,max = 80)
    private String email;

    @NotBlank(message = "Please login")
    private String login;

    @Size(max = 100)
    @NotBlank(message = "Please provide your password")
    private String password;

}
