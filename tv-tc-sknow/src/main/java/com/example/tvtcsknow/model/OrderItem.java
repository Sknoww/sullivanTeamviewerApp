package com.example.tvtcsknow.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private long id;

        @ManyToOne
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;

        @ManyToOne
        @JoinColumn(name = "order_id", nullable = false)
        private Order order;

        @Column(name = "quantity", nullable = false)
        private int quantity;

}
