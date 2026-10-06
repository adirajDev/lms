package dev.adi.lms.features.member;

import dev.adi.lms.features.member.dtos.MemberRequest;
import dev.adi.lms.features.member.dtos.MemberResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public PagedModel<MemberResponse> list(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 20, sort = "fullName", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return new PagedModel<>(memberService.search(name, pageable));
    }

    @GetMapping("/{id}")
    public MemberResponse get(@PathVariable Long id) {
        return memberService.get(id);
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest req) {
        MemberResponse created = memberService.create(req);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public MemberResponse update(@PathVariable Long id, @Valid @RequestBody MemberRequest req) {
        return  memberService.update(id, req);
    }

    @PostMapping("/{id}/suspend")
    public MemberResponse suspend(@PathVariable Long id) {
        return memberService.suspend(id);
    }

    @PostMapping("/{id}/reactivate")
    public MemberResponse reactivate(@PathVariable Long id) {
        return memberService.reactivate(id);
    }

    @PostMapping("/{id}/close")
    public MemberResponse close(@PathVariable Long id) {
        return memberService.close(id);
    }
}
