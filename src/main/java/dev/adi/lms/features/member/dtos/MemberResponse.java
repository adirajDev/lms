package dev.adi.lms.features.member.dtos;

import dev.adi.lms.features.member.Member;
import dev.adi.lms.features.member.MemberStatus;

import java.time.LocalDate;

public record MemberResponse(
        Long id,
        String email,
        String fullName,
        LocalDate joinedOn,
        MemberStatus status
) {
    public static MemberResponse from(Member m) {
        return new MemberResponse(m.getId(), m.getEmail(), m.getFullName(), m.getJoinedOn(), m.getStatus());
    }
}
