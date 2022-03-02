package demo.springbootdemo;

import demo.springbootdemo.aop.TimeTraceAop;
import demo.springbootdemo.repository.JpaMemberRepository;
import demo.springbootdemo.repository.MemberRepository;
import demo.springbootdemo.repository.MemoryMemberRepository;
import demo.springbootdemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 컴포넌트 스캔 방식 외에, 직접 코드를 작성해 스프링 빈을 등록하기
@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        // 인자로 받는 memberRepository는 스프링이 자체적으로 생성해 Bean으로 가지고 있을 SpringDataJpaMemberRepository의 구현체의 객체.
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); // MemberService 객체를 생성해서 스프링 컨테이너에 등록
    }
}
