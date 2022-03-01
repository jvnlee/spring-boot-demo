package demo.springbootdemo;

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

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // MemberService 객체를 생성해서 스프링 컨테이너에 등록
    }

    @Bean
    public MemberRepository memberRepository() {
        /*
        return new MemoryMemberRepository();
        MemoryMemberRepository 객체를 생성해서 스프링 컨테이너에 등록
         */
        return new JpaMemberRepository(em);
    }
    /*
    MemberRepository처럼 구현체가 바뀔 수 있는 경우는 이렇게 SpringConfig로 지정해주면 편리함
    return문에서 생성할 객체의 클래스명만 바꿔주면 되기 때문
     */
}
