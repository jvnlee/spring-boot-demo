package demo.springbootdemo.repository;

import demo.springbootdemo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    /*
    JpaRepository를 상속 받은 이 인터페이스는 구현체를 따로 만들지 않아도 됨. 스프링이 구동 시에 구현체와 그 객체를 알아서 만들고 Bean으로 보관하기 때문.
    스프링 데이터 JPA는 단순 조회나 기본적인 CRUD에 필요한 범용 메서드를 모두 제공하기 때문에, 아래의 findByName 같이 범용이 아닌 특수한 메서드만 따로 선언해주면 됨
     */

    @Override
    Optional<Member> findByName(String name);
    /*
    스프링 데이터 JPA는 메서드의 이름만 보고도 추론하는 능력이 있음
    네이밍 컨벤션에 맞게 findBy~로 이름을 지으면 해당 필드를 이용한 쿼리를 구현해줌
     */
}
