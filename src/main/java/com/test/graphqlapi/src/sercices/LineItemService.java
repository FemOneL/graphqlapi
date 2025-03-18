package com.test.graphqlapi.src.sercices;

import com.test.graphqlapi.src.entities.LineItemEntity;
import com.test.graphqlapi.src.repositories.LineItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LineItemService {
    private final LineItemRepository lineItemRepository;

    public List<LineItemEntity> findAll() {
        return lineItemRepository.findAll();
    }

    public LineItemEntity findById(Long id) {
        return lineItemRepository.findById(id).orElse(null);
    }

    public LineItemEntity save(LineItemEntity lineItemEntity) {
        return lineItemRepository.save(lineItemEntity);
    }

    public void deleteById(Long id) {
        lineItemRepository.deleteById(id);
    }
}