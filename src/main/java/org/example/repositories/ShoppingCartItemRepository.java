package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public class ShoppingCartItemRepository extends JpaRepository {
    EntityManager entityManager;

    public ShoppingCartItemRepository(EntityManager entityManager) {
        super(entityManager, ShoppingCartItem.class);
        this.entityManager = entityManager;
    }

    public void create(ShoppingCartItem cartItem) {
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

    public Optional<ShoppingCartItem> findById(Long id) {
        return Optional.ofNullable(entityManager.find(ShoppingCartItem.class, id));
    }

    public List<ShoppingCartItem> getAll() {
        var jpql = "SELECT i FROM ShoppingCartItem i";
        var query = entityManager.createQuery(jpql, ShoppingCartItem.class);
        return query.getResultList();
    }

    public void update(ShoppingCartItem cartItem) {}
    public void deleteById(Long id) {}
}
