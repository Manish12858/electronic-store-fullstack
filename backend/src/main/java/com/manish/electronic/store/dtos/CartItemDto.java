package com.manish.electronic.store.dtos;

import com.manish.electronic.store.entities.Cart;
import com.manish.electronic.store.entities.CartItem;
import com.manish.electronic.store.entities.Product;
import com.manish.electronic.store.entities.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {

    private int cartItemId;
    private ProductDto product;
    private int quantity;
    private int totalPrice;

}
