package demo.springbootdemo.controller;

import demo.springbootdemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
컴포넌트 스캔으로 스프링 빈 등록하기

@Controller, @Service, @Repository 등은 모두 소스코드 상 @Component의 일종
스프링은 구동 시 이러한 컴포넌트 애너테이션이 등록된 클래스를 찾아 디렉토리를 스캔하고, 발견 시 그들의 객체를 생성해서 스프링 컨테이너에 보관함
 */
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        /*
        Dependency Injection

        컨트롤러인 MemberController의 객체 생성 시점에, 생성에 필요한 MemberService의 객체를 스프링 컨테이너에서 찾아서 주입시켜줌
        스프링 빈은 스프링 컨테이너 내에 싱글톤(유일한 객체)으로 등록되기 때문에 같은 스프링 빈이면 같은 인스턴스라고 볼 수 있음
         */
    }
}
