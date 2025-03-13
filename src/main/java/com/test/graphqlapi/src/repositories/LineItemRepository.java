package com.test.graphqlapi.src.repositories;

import com.test.graphqlapi.src.entities.LineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItemEntity, Long> {
}