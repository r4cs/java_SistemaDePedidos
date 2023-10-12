package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.repositories.ShoppingCartItemRepository;

public class Main {
    public static void main(String[] args) {

        System.out.println(".");
        System.out.println("..");
        System.out.println("...");
        System.out.println("Simulado rodando !!");

        try {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        var cartItemRepository = new ShoppingCartItemRepository(manager);

        manager.close();
        factory.close();

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ): ");
            throw e;
            }
        }
}