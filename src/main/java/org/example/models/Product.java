package org.example.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.models.base.BaseEntity;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_PRODUCT")
public class Product extends BaseEntity {

    private Double price;
    private Integer quantity;
}
