package demo.springbootdemo.repository;

import demo.springbootdemo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트 메서드가 실행된 이후에 호출되는 콜백 메서드
    @AfterEach
    public void afterEach() {
        /*
        이 테스트 클래스 내에서는 각 테스트 메서드 실행 후 repository에 저장된 데이터를 비워주지 않으면
        서로 다른 테스트 메서드에서 repository에 추가한 데이터들이 엉켜 충돌이 일어날 수 있기 때문에 꼭 비워줘야 함
         */
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("andy");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 새로 생성해서 저장한 멤버인 member와 리포지토리에서 id로 조회해서 찾아낸 멤버인 result가 서로 같다면, 제대로 save()가 이루어졌다고 볼 수 있음
        // 아래 메서드로 비교하여, 두 객체가 같다면 테스트 결과에 녹색 체크 표시가 뜸
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member mem1 = new Member();
        mem1.setName("spring1");
        repository.save(mem1);

        Member mem2 = new Member();
        mem2.setName("spring2");
        repository.save(mem2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(mem1);
    }

    @Test
    public void findAll() {
        Member mem1 = new Member();
        mem1.setName("spring1");
        repository.save(mem1);

        Member mem2 = new Member();
        mem2.setName("spring2");
        repository.save(mem2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
