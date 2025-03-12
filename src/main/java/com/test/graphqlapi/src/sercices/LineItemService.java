package com.test.graphqlapi.src.sercices;

import com.test.graphqlapi.src.entities.LineItem;
import com.test.graphqlapi.src.repositories.LineItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LineItemService {
    private final LineItemRepository lineItemRepository;

    public List<LineItem> findAll() {
        return lineItemRepository.findAll();
    }

    public LineItem findById(Long id) {
        return lineItemRepository.findById(id).orElse(null);
    }

    public LineItem save(LineItem lineItem) {
        return lineItemRepository.save(lineItem);
    }

    public void deleteById(Long id) {
        lineItemRepository.deleteById(id);
    }
}