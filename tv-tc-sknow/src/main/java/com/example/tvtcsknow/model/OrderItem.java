package com.example.tvtcsknow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order_item")
@NoArgsConstructor
public class OrderItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @ManyToOne
        @JoinColumn(name = "order_id")
        @JsonIgnoreProperties("orderItems")
        private Order order;

        @Column(name = "quantity", nullable = false)
        private int quantity;

        // Necessary to avoid StackOverflow error when serializing
        @Override
        public String toString() {
                return "OrderItem{" +
                        "id=" + id +
                        ", order=" + (order != null ? order.getId() : null) +
                        ", product=" + (product != null ? product.getId() : null) +
                        ", quantity=" + quantity +
                        '}';
        }


}
