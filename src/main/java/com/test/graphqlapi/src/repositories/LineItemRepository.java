package com.test.graphqlapi.src.repositories;

import com.test.graphqlapi.src.entities.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
}