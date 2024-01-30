package com.example.tvtcsknow.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "order_item")
@NoArgsConstructor
public class OrderItem {

        @Id
        @Getter
        @Setter
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private long id;

        @Getter
        @Setter
        @ManyToOne
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;

        @Getter
        @Setter
        @ManyToOne
        @JoinColumn(name = "order_id", nullable = false)
        private Order order;

        @Getter
        @Setter
        @Column(name = "quantity", nullable = false)
        private int quantity;

}
