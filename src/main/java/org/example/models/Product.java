package org.example.models;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import org.example.models.base.BaseEntity;
import java.util.List;

public class Product extends BaseEntity {
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @ManyToMany(mappedBy = "products")
    private List<ShoppingCartItem> shoppingCartItems;
}
