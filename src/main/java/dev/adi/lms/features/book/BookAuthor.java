package dev.adi.lms.features.book;

import dev.adi.lms.features.author.Author;   // adjust to your package
import jakarta.persistence.*;

@Entity
@Table(name = "book_authors")
public class BookAuthor {

    @EmbeddedId
    private BookAuthorId id = new BookAuthorId();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "author_order", nullable = false)
    private short authorOrder;

    protected BookAuthor() {}   // JPA

    BookAuthor(Book book, Author author, short authorOrder) {
        this.book = book;
        this.author = author;
        this.authorOrder = authorOrder;
    }

    public Book getBook()          { return book; }
    public Author getAuthor()      { return author; }
    public short getAuthorOrder()  { return authorOrder; }
    void setAuthorOrder(short o)   { this.authorOrder = o; }
}