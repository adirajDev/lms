package dev.adi.lms.features.author.dtos;

import dev.adi.lms.features.author.Author;

public record AuthorResponse(
        Long id,
        String fullName,
        Short birthYear,
        String country
) {
    public static AuthorResponse from(Author a) {
        return new AuthorResponse(a.getId(), a.getFullName(), a.getBirthYear(), a.getCountry());
    }
}
