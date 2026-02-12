package academy.kata.abalashov.pp_3_1_1.dto;

import jakarta.validation.constraints.NotNull;

public record AddUserRequest(
    @NotNull String name,
    @NotNull String lastName,
    @NotNull Byte age
) {
}
