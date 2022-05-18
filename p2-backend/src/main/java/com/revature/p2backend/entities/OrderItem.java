package com.revature.p2backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "order_item" , schema="public")
@JsonIgnoreProperties
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Integer OrderItem;

    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id" , referencedColumnName = "order_id" )
    @JsonBackReference
    private Orders orders  ;

    @Column(name="item_total_amount")
    private Double itemTotalAmount;//changed to Double


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id" , referencedColumnName = "product_id")
    private Product productId;

    public OrderItem(Integer quantity, Product productId, Orders orders) {
        this.quantity = quantity;
        this.productId = productId;
        this.orders = orders;
    }

    public OrderItem() {
        
    }

    public Integer getOrderItem() {
        return OrderItem;
    }

    public void setOrderItem(Integer orderItem) {
        OrderItem = orderItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Double getItemTotalAmount() {
        return itemTotalAmount;
    }

    public void setItemTotalAmount(Double itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "OrderItem=" + OrderItem +
                ", quantity=" + quantity +
//                ", orders=" + orders +
                ", itemTotalAmount=" + itemTotalAmount +
                ", productId=" + productId +
                '}';
    }
}
