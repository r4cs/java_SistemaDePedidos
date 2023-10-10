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
@Table(name="TB_ORDER_TEM")
public class OrderItem extends BaseEntity {

    private Integer order_id;
    private Integer product_id;
    private Integer quantity;
    private Float price;
}
