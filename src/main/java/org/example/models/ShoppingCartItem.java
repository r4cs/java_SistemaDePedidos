package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name="TB_SHP_CART_ITEM")
public class ShoppingCartItem extends BaseEntity {

    private String customer_id;
    private Integer product_id;
    private Integer quantity;
    private Float price;
}
