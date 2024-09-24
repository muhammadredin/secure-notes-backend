package io.github.muhammadredin.securenotesbackend.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private String path;
    private String message;
    private List<String> errors;
    private int status;
}
