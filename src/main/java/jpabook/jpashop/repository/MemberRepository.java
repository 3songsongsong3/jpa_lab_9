package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
        @Repository 어노테이션이 붙어 있으면 <context:component-scan>에 의해
        스프링 빈으로 자동 등록 된다.
        그리고 JPA 전용 예외가 발생하면 스프링이 추상화한 예외로 변환해준다.
*/
@Repository
public class MemberRepository {

    /*
        @PersistenceContext
        스프링이나 J2EE 컨테이너를 사용하면 컨테이너가 엔티티 매니저를 관리하고 제공해준다.
     */
    @PersistenceContext // 엔티티 매니저 주입
    EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
