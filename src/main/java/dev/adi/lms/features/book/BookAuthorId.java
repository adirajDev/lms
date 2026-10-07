package dev.adi.lms.features.book;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorId implements Serializable {
    @Column(name = "book_id")
    private String bookId;

    @Column(name = "author_id")
    private String authorId;

    protected BookAuthorId() {}

    public BookAuthorId(String bookId, String authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getAuthorId() {
        return authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookAuthorId other)) return false;
        return Objects.equals(bookId, other.bookId)
                && Objects.equals(authorId, other.authorId);
    }

    @Override
    public int hashCode() {return Objects.hash(bookId, authorId);}
}
