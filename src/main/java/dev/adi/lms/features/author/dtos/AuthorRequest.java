package dev.adi.lms.features.author.dtos;

import jakarta.validation.constraints.*;

public record AuthorRequest(
        @NotBlank @Size(max = 120) String fullName,
        @Min(1400) @Max(2020) Short birthYear,
        @Pattern(regexp = "^[A-Z]{2}$",
                message = "must be an ISO 3166-1 alpha-2 code, e.g. IN") String country  // optional
) {}
