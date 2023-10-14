package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.OrderItem;

public class OrderItemRepository extends JpaRepository{

    EntityManager entityManager;

    public OrderItemRepository(EntityManager entityManager) {
        super(entityManager, OrderItem.class);
        this.entityManager = entityManager;
    }
}
