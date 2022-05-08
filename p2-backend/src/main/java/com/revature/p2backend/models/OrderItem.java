package com.revature.p2backend.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "order_item" , schema="public")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Integer OrderItem;

    @Column
    private Integer quantity;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="order_id" , referencedColumnName = "order_id" )
    private Orders orders  ;

    @Column(name="item_total_amount")
    private BigDecimal itemTotalAmount;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="product_id" , referencedColumnName = "product_id")
    private Product productId;

    public OrderItem(Integer quantity, Product productId,Orders orders) {
        this.quantity = quantity;
        this.productId = productId;
        this.orders = orders;
    }



}
