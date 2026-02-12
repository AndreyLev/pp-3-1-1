package academy.kata.abalashov.pp_3_1_1.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
    @NotNull Long id,
    @NotNull String name,
    @NotNull String lastName,
    @NotNull Byte age
) {
}
