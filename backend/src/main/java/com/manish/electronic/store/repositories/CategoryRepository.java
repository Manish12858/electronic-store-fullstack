package com.manish.electronic.store.repositories;

import com.manish.electronic.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String>
{
}
