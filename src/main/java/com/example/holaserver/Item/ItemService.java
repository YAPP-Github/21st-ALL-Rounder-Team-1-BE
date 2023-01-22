package com.example.holaserver.Item;

import com.example.holaserver.Item.DTO.ItemSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Long> saveItems(Long storeId, ItemSaveDto[] itemDto) {
        List<Item> items = Arrays.stream(itemDto).map(item -> item.createSaveItemBuilder(storeId))
                .collect(Collectors.toList());
        return itemRepository.saveAll(items).stream().map(Item::getId)
                .collect(Collectors.toList());
    }
}
