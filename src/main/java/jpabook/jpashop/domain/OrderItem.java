package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Order order;

    private int orderPrice;

    private int count;

}
