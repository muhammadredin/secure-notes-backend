package io.github.muhammadredin.securenotesbackend.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;
}
