package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.OrderItem;
import org.example.models.Product;

public class ProductRepository extends JpaRepository{
    EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        super(entityManager, Product.class);
        this.entityManager = entityManager;
    }
}
