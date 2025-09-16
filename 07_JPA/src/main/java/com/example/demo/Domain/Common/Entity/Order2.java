package com.example.demo.Domain.Common.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name="order2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order2 {
   @EmbeddedId
    private OrderId2 id;
    private int quantity;
}