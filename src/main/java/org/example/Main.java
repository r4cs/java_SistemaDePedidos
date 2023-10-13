package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.enums.OrderStatusCode;
import org.example.models.*;
import org.example.repositories.ShoppingCartItemRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        Customer customer1 = new Customer();
        Order order = new Order();
        order.setCustomer(customer1);

        List<OrderItem> orderItemsList = new ArrayList<>();
        ShoppingCartItem cartItem = new ShoppingCartItem();

        OrderItem orderItem = new OrderItem();
        orderItem.setCartItem(cartItem);
        orderItem.setQuantity(2);
        orderItem.setOrder(order);

        orderItemsList.add(orderItem);

        order.setItems(orderItemsList);
        order.setStatusCode(OrderStatusCode.PROCESSING);
        order.setCustomer_comments("Comment here!");

        ShoppingCartItemRepository repository = new ShoppingCartItemRepository(manager);
        repository.insertCartItem(cartItem);
    }
}