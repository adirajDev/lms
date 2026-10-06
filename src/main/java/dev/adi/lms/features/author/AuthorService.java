package dev.adi.lms.features.author;

import dev.adi.lms.common.exception.ResourceNotFoundException;
import dev.adi.lms.features.author.dtos.AuthorRequest;
import dev.adi.lms.features.author.dtos.AuthorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public Page<AuthorResponse> search(String name, Pageable pageable) {
        Page<Author> page = (name == null || name.isBlank())
                ? authorRepository.findAll(pageable)
                : authorRepository.findByFullNameContainingIgnoreCase(name.trim(), pageable);
        return page.map(AuthorResponse::from);
    }

    @Transactional(readOnly = true)
    public AuthorResponse get(Long id) {
        return AuthorResponse.from(findOrThrow(id));
    }

    @Transactional
    public AuthorResponse create(AuthorRequest req) {
        Author author = new Author(req.fullName(), req.birthYear(), req.country());
        return AuthorResponse.from(authorRepository.save(author));
    }

    @Transactional
    public AuthorResponse update(Long id, AuthorRequest req) {
        Author author = findOrThrow(id);
        author.setFullName(req.fullName());
        author.setBirthYear(req.birthYear());
        author.setCountry(req.country());
        return AuthorResponse.from(author);
    }

    @Transactional
    public void delete(Long id) {
        Author author = findOrThrow(id);
        authorRepository.delete(author);
    }

    private Author findOrThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", id));
    }
}
