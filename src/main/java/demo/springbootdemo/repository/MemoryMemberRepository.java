package demo.springbootdemo.repository;

import demo.springbootdemo.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    // store과 sequence 모두 간단한 예시 상황이기 때문에 동시성 문제는 고려하지 않고 만듦
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 순차적인 id 생성을 위해

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
