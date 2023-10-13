package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.models.base.BaseEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_SHOPPING_CART_ITEM")
public class ShoppingCartItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(targetEntity = Product.class,
                fetch = FetchType.EAGER)
    @JoinTable(name = "product_id",
                joinColumns = @JoinColumn(name = "shopping_cart_item_id"),
                inverseJoinColumns = @JoinColumn(name="product_id"))
    private Product product;


    private Integer quantity;
    private Double price;
}
