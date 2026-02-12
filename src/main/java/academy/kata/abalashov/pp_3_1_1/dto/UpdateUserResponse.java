package academy.kata.abalashov.pp_3_1_1.dto;

public record UpdateUserResponse(
    Long id,
    String name,
    String lastName,
    Byte age
) {
}
