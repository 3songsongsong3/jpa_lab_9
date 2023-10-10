package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager em;

    /**
     * 이 메소드 하나로 저장과 수정(병합)을 모두 처리
     * 식별자 값이 없으면 새로운 엔티티로 판단해서 persist로 영속화,
     * 식별자 값이 있으면 이미 한번 영속화 되었던 엔티티로 판단해서 merge()로 수정(병합)한다.
     *
     * 이렇게 함으로써 이 메소드를 사용하는 클라이언트는 저장과 수정을 구분하지 않아도 되므로
     * 클라이언트의 로직이 단순해진다.
     * @param item
     */
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
