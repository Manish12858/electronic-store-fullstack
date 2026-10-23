package com.manish.electronic.store.dtos;

import com.manish.electronic.store.entities.Order;
import com.manish.electronic.store.entities.Product;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemDto {


    private int orderItemId;

    private int quantity;

    private int totalPrice;

    private ProductDto product;


}
