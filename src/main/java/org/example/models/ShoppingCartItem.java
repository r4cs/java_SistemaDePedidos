package org.example.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.models.base.BaseEntity;

import java.util.List;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_SHOPPING_CART_ITEM")
public class ShoppingCartItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product_id;

    private Integer quantity;
    private Double price;
}
