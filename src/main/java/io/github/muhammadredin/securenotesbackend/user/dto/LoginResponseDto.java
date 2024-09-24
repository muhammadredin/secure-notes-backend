package io.github.muhammadredin.securenotesbackend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private String username;
    private List<String> roles;
}
