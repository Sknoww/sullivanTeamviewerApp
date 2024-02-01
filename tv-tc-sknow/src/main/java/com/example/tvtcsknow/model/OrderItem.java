package com.example.tvtcsknow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
