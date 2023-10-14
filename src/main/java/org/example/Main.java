package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.enums.OrderStatusCode;
import org.example.models.Customer;
import org.example.models.Order;
import org.example.models.OrderItem;
import org.example.models.ShoppingCartItem;
import org.example.models.Product;
import org.example.repositories.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(".");
        System.out.println("..");
        System.out.println("...");
        System.out.println("Simulado rodando !!");

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
            EntityManager manager = factory.createEntityManager();

            Customer customer1 = new Customer("customer1@mail.com");
            Order order = new Order();
            order.setCustomer_id(customer1);
            order.setCustomer_comments("Customer 1 comment 1");
            order.setStatus_code_id(OrderStatusCode.PENDING);

            Product prod1 = new Product(9.99, 2);
            ShoppingCartItem cartItem = new ShoppingCartItem();
            cartItem.setCustomer_id(customer1);
            cartItem.setProduct_id(prod1);
            cartItem.setQuantity(prod1.getQuantity());
            cartItem.setPrice(prod1.getPrice());


            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_id(order);
            orderItem.setProduct_id(prod1);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());


            CustomerRepository customerRepository = new CustomerRepository(manager);
            customerRepository.create(customer1);

            ProductRepository productRepository = new ProductRepository(manager);
            productRepository.create(prod1);

            OrderRepository orderRepository = new OrderRepository(manager);
            orderRepository.create(order);

            ShoppingCartItemRepository cartItemRepository = new ShoppingCartItemRepository(manager);
            cartItemRepository.create(cartItem);

            OrderItemRepository orderItemRepository = new OrderItemRepository(manager);
            orderItemRepository.create(orderItem);


            manager.close();
            factory.close();

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ): ");
            throw e;
            }
        }
}