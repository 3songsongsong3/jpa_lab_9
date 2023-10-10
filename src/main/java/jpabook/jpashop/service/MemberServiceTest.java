package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // JUnit 테스트 케이스를 스프링 프레임워크와 통합 (테스트가 스프링 컨테이너에서 실행)
@ContextConfiguration(locations = "classpath:appConfig.xml") // 테스트 케이스 실행시 지정할 스프링 설정 정보
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // Given (테스트할 상황 설정)
        Member member = new Member();
        member.setName("kim");

        // When (테스트 대상 실행)
        Long saveId = memberService.join(member);

        // Then (테스트 결과 검증)
        assertEquals(member, memberRepository.findOne(saveId));
    }
}
