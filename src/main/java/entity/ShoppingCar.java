package entity;

import java.io.Serializable;

public class ShoppingCar implements Serializable {
    private int userId;
    private int productId;
    private int productPrice;
    private int counts;

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", productPrice=" + productPrice +
                ", counts=" + counts +
                '}';
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
}
