package dev.godradam.matchball.api.dto;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDTO {
    private @NonNull String username;
    private @NonNull String password;
}
