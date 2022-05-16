package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.ProductDao;
import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.Product;
import org.hibernate.Session;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class CartService {

    private final ProductDao productDao;

    //private final StorageManager storageManager;
    //private Session session;
    private  boolean running = false;


    public CartService(ProductDao productDao, StorageManager storageManager){
        //this.storageManager = storageManager;
        this.productDao = productDao;
    }

    HashMap<Product, Integer> cart = new HashMap<>();

    public void addProduct(Product product, Integer quantity){
        cart.put(product, quantity);
        //session.persist(product);
    }

    public void removeProduct(Product product){
        cart.remove(product);
    }


    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<Product, Integer> cart) {
        this.cart = cart;
    }

//    /**
//     * This method will start the component and establish a usable session
//     * for the rest of the class.
//     */
//    @Override
//    public void start() {
//        this.session = storageManager.getSession();
//        running = true;
//    }
//
//    /**
//     * Will destroy the connection when complete.
//     */
//    @Override
//    public void stop() {
//        running = false;
//        session.close();
//    }
//
//    /**
//     * This will return a boolean if this component/bean is running.
//     * @return
//     */
//    @Override
//    public boolean isRunning() {
//        return running;
//    }

}
