package entity;

import java.io.Serializable;

public class ShoppingRecord implements Serializable {
    private int userId;
    private int productId;
    private String time;
    private int orderStatus;
    private int productPrice;
    private int counts;
    private int shangjiaId;

    public int getShangjiaId() {
        return shangjiaId;
    }

    public void setShangjiaId(int shangjiaId) {
        this.shangjiaId = shangjiaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "ShoppingRecord{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", time='" + time + '\'' +
                ", orderStatus=" + orderStatus +
                ", productPrice=" + productPrice +
                ", counts=" + counts +
                '}';
    }
}
