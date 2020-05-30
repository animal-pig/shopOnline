package service;

import entity.ShoppingCar;

import java.util.List;

public interface ShoppingCarService {
    public ShoppingCar getShoppingCar(int userId, int productId);

    public void addShoppingCar(ShoppingCar shoppingCar);

    public boolean deleteShoppingCar(int userId, int productId);

    public boolean updateShoppingCar(ShoppingCar shoppingCar);

    public List<ShoppingCar> getShoppingCars(int userId);
}
