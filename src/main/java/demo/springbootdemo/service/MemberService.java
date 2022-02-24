package demo.springbootdemo.service;

import demo.springbootdemo.domain.Member;
import demo.springbootdemo.repository.MemberRepository;
import demo.springbootdemo.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 의존성 주입 (Dependency Injection)
    }

    // 회원 가입
    public Long join(Member member) {
        // 중복된 이름의 회원이 있으면 안됨
        validateExistingName(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateExistingName(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 이름입니다.");
                });
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
