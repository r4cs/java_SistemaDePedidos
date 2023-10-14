package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.models.base.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ORDER_ITEM")
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_id;

//    @ManyToOne
//    @JoinColumn(name = "shopping_cart_item_id")
//    private ShoppingCartItem cartItem;

    private Integer quantity;
    private Double price;
}
