package dev.adi.lms.features.book;

import dev.adi.lms.features.book.dtos.BookResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public PagedModel<BookResponse> list(
            @RequestParam(required = false) String title,
            @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return new PagedModel<BookResponse>(bookService.search(title, pageable));
    }
}
