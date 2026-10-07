package dev.adi.lms.features.book;

import dev.adi.lms.features.author.Author;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    @Column(nullable = false, length = 300)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookGenre genre;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(nullable = false, length = 2)
    private String language;

    @Column(nullable = false, name = "published_year")
    private Short publishedYear;

    @Column(name = "page_count")
    private Integer pageCount;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("authorOrder")
    private List<BookAuthor> authors = new ArrayList<>();

    protected Book() {}

    public Book(Integer pageCount, Short publishedYear, String language,
                BookGenre genre, String title, String isbn) {
        this.pageCount = pageCount;
        this.publishedYear = publishedYear;
        this.language = language;
        this.genre = genre;
        this.title = title;
        this.isbn = isbn;
    }

    public void addAuthor(Author author, short order) {
        authors.add(new BookAuthor(this, author, order));
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public List<BookAuthor> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Short getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Short publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", language='" + language + '\'' +
                ", publishedYear=" + publishedYear +
                ", pageCount=" + pageCount +
                ", id=" + id +
                '}';
    }
}
