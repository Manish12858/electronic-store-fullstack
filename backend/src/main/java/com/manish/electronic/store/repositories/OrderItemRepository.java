package com.manish.electronic.store.repositories;

import com.manish.electronic.store.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>
{
}
