package com.revature.p2backend;

public class CartItem {

    private Double itemTotalAmount;
    private Integer quantity;
    private Integer orderId;
    private Integer productId;



    public Double getItemTotalAmount() {
        return itemTotalAmount;
    }

    public void setItemTotalAmount(Double itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}


