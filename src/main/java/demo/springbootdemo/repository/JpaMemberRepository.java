package demo.springbootdemo.repository;

import demo.springbootdemo.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // 스프링이 제공해주는 entity 관리자
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
        // 스프링 구동 시 EntityManager 객체가 생성되기 때문에 이미 생성된 걸 주입받기만 하면 됨
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // SQL로 insert문 쓸 필요 없이 JPA가 알아서 데이터를 DB에 저장해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
        // DB의 Member 테이블이 아니라, Entity로 지정한 Member 객체에 대해 쿼리(JPQL)를 날림
    }
}
