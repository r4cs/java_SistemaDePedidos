package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Customer;
import org.example.models.OrderItem;

public class CustomerRepository extends JpaRepository{
    EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        super(entityManager, Customer.class);
        this.entityManager = entityManager;
    }
}
