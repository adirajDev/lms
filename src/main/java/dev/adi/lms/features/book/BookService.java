package dev.adi.lms.features.book;

import dev.adi.lms.features.book.dtos.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> search(
            String title,
            Pageable pageable
    ) {
        Page<Book> page = (title == null || title.isBlank())
                ? bookRepository.findAll(pageable)
                : bookRepository.findByTitleContainingIgnoreCase(title, pageable);

        return page.map(BookResponse::from);
    }
}
