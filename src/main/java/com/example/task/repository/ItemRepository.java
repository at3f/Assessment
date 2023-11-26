package com.example.task.repository;

import com.example.task.constant.ItemType;
import com.example.task.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNameAndType(String name, ItemType type);
}

