package com.example.holaserver.Item;

import com.example.holaserver.Item.DTO.ItemSaveBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Map<String, Object> saveItems(Long storeId, ItemSaveBody[] itemSaveBodies) {
        List<Item> items = Arrays.stream(itemSaveBodies).map(item -> item.createSaveItemBuilder(storeId))
                .collect(Collectors.toList());
        ModelAndView result = new ModelAndView();
        List<Long> itemIds = itemRepository.saveAll(items).stream().map(Item::getId)
                .collect(Collectors.toList());
        result.addObject("itemIds", itemIds);
        return result.getModel();
    }
}
