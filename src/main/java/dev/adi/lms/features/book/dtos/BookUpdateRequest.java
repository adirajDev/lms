package dev.adi.lms.features.book.dtos;

import dev.adi.lms.features.book.BookGenre;
import jakarta.validation.constraints.*;

import java.util.List;

public record BookUpdateRequest(
        @NotBlank @Size(max = 300)
        String title,

        @NotNull
        BookGenre genre,

        @Pattern(regexp = "[a-z]{2}", message = "must be a 2-letter lowercase code")
        String language,                       // optional, service defaults to "en"

        @NotNull @Min(1450) @Max(2030)
        Short publishedYear,

        @Positive
        Integer pageCount,                     // optional

        @NotEmpty @Size(max = 20)
        List<@NotNull @Positive Long> authorIds   // order = list position
) {}
