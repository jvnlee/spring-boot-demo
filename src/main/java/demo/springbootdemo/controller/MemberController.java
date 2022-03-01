package demo.springbootdemo.controller;

import demo.springbootdemo.domain.Member;
import demo.springbootdemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createMemberForm() {
        return "members/createMemberForm";
    }

    // 맵핑할 URL이 같아도 GET이냐 POST냐에 따라 다른 로직을 적용할 수 있음
    @PostMapping("/members/new")
    public String createMember(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
