package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public class ShoppingCartItemRpository {
    EntityManager entityManager;

    public ShoppingCartItemRpository(EntityManager entityManager) {
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

    public void updateCartItem() {

    }
    public void deleteCartItem() {

    }
}
