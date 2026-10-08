package dev.adi.lms.features.book.dtos;

import dev.adi.lms.features.book.Book;
import dev.adi.lms.features.book.BookGenre;

import java.util.List;

public record BookResponse(
    Long id,
    String isbn,
    String title,
    BookGenre genre,
    String language,
    Short publishedYear,
    Integer pageCount,
    List<BookAuthorResponse> authors
) {
    public static BookResponse from(Book b) {
        return new BookResponse(
                b.getId(),
                b.getIsbn(),
                b.getTitle(),
                b.getGenre(),
                b.getLanguage(),
                b.getPublishedYear(),
                b.getPageCount(),
                b.getAuthors().stream()
                        .map(BookAuthorResponse::from)
                        .toList()
        );
    }
}
