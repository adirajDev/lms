package dev.adi.lms.features.book.dtos;

import dev.adi.lms.features.book.BookAuthor;

public record BookAuthorResponse(
        Long id,
        String fullName
) {
    public static BookAuthorResponse from(BookAuthor bookAuthor) {
        return new BookAuthorResponse(
                bookAuthor.getAuthor().getId(),
                bookAuthor.getAuthor().getFullName()
        );
    }
}
