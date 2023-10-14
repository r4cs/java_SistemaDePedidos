package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Order;

public class OrderRepository extends JpaRepository{
    EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        super(entityManager, Order.class);
        this.entityManager = entityManager;
    }
}
