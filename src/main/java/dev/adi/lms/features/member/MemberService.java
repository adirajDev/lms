package dev.adi.lms.features.member;

import dev.adi.lms.common.exception.ResourceNotFoundException;
import dev.adi.lms.features.member.dtos.MemberRequest;
import dev.adi.lms.features.member.dtos.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public Page<MemberResponse> search(String name, Pageable pageable) {
        Page<Member> page = (name == null || name.isBlank())
                ? memberRepository.findAll(pageable)
                : memberRepository.findByFullNameContainingIgnoreCase(name, pageable);
        return page.map(MemberResponse::from);
    }

    @Transactional(readOnly = true)
    public MemberResponse get(Long id) {
        return MemberResponse.from(findOrThrow(id));
    }

    @Transactional
    public MemberResponse create(MemberRequest req) {
        Member member = new Member(req.email(), req.fullName());
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public MemberResponse update(Long id, MemberRequest req) {
        Member member = findOrThrow(id);
        member.setFullName(req.fullName());
        member.setEmail(req.email());
        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse suspend(Long id) {
        Member member = findOrThrow(id);
        // TODO: check if any loan is active for member before suspending
        // TODO: do this after writing the loan feature
        member.setStatus(MemberStatus.SUSPENDED);
        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse reactivate(Long id) {
        Member member = findOrThrow(id);
        member.setStatus(MemberStatus.ACTIVE);
        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse close(Long id) {
        Member member = findOrThrow(id);
        // TODO: check if any loan is active for member before closing an account with proper error
        // TODO: do this after writing the loan feature
        member.setStatus(MemberStatus.CLOSED);
        return MemberResponse.from(member);
    }

    private Member findOrThrow(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", id));
    }
}
