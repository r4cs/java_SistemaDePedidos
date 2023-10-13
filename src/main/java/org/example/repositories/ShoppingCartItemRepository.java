package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public class ShoppingCartItemRepository {
    EntityManager entityManager;

    public ShoppingCartItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insertCartItem(ShoppingCartItem cartItem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cartItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.err.println("Erro em insertCartItem(): ");
            throw e;
        }
    }

    public Optional<ShoppingCartItem> getCartItemById(Long id) {
        return Optional.ofNullable(entityManager.find(ShoppingCartItem.class, id));
    }

    public List<ShoppingCartItem> getAllCartItems() {
        var jpql = "SELECT i FROM ShoppingCartItem i";
        var query = entityManager.createQuery(jpql, ShoppingCartItem.class);
        return query.getResultList();
    }

    public void updateCartItem(ShoppingCartItem cartItem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cartItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro em updateCartItem");
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    public void deleteCartItem(Long id) {
        try {
            var cartItem = getCartItemById(id);
            if (cartItem.isPresent()) {
                entityManager.getTransaction().begin();
                entityManager.remove(cartItem);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("Erro em deleteCartItem");
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
