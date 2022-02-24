package demo.springbootdemo.service;

import demo.springbootdemo.domain.Member;
import demo.springbootdemo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 각 테스트 메서드 실행 전에 먼저 실행할 로직
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given - when - then 3단계를 생각하며 테스트 코드 작성하는 것을 권장

        // given
        Member member = new Member();
        member.setName("andy");

        // when
        Long savedId = memberService.join(member);

        // then
        Member foundMember = memberService.findMember(savedId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    // 이 테스트 메서드는 join()에서 중복된 이름으로 가입을 시도할 시 예외가 제대로 던져지는지 확인하는 용도
    @Test
    public void existingNameCheck() {
        // given
        Member mem1 =new Member();
        mem1.setName("spring");

        Member mem2 =new Member();
        mem2.setName("spring");

        // when
        memberService.join(mem1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(mem2));// (터져야할 예외, 예외가 발생할 로직)
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 이름입니다.");

        /*
        try {
            memberService.join(mem2);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 이름입니다.");
        }
        */
    }

    @Test
    void findAllMembers() {
        // given
        Member mem1 = new Member();
        mem1.setName("spring1");

        Member mem2 = new Member();
        mem2.setName("spring2");

        // when
        memberRepository.save(mem1);
        memberRepository.save(mem2);

        // then
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findMember() {
        // given
        Member member = new Member();
        member.setName("andy");

        // when
        memberRepository.save(member);

        // then
        assertThat(memberRepository.findById(1L)).isEqualTo(memberService.findMember(1L));
    }
}