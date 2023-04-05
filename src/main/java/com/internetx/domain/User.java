package com.internetx.domain;

import com.internetx.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

}
