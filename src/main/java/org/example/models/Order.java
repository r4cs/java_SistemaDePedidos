package org.example.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.models.base.BaseEntity;
import org.example.enums.OrderStatusCode;

import java.util.List;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ORDER")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer_id;

    @Enumerated(EnumType.STRING)
    private OrderStatusCode status_code_id;

    private String customer_comments;

//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderItem> order_id;
}
