package demo.springbootdemo.service;

import demo.springbootdemo.domain.Member;
import demo.springbootdemo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
// Spring 위에서 테스트 시킴
@Transactional
// @Transactional 애너테이션을 테스트 클래스에 붙이면, 테스트 실행으로 발생하는 데이터 트랜잭션이 테스트를 마친 이후 롤백되어 결과적으로 DB에는 영향을 주지 않게됨
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long savedId = memberService.join(member);

        // then
        Member foundMember = memberService.findMember(savedId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    public void existingNameCheck() {
        // given
        Member mem1 = new Member();
        mem1.setName("spring");

        Member mem2 = new Member();
        mem2.setName("spring");

        // when
        memberService.join(mem1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(mem2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 이름입니다.");
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
