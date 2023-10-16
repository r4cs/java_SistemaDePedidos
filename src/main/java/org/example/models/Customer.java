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
@Table(name = "TB_CUSTOMER")
public class Customer {
    @Id
    @Column(length = 50)
    private String email;
}
