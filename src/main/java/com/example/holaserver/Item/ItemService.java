package com.example.holaserver.Item;

import com.example.holaserver.Item.DTO.ItemSaveBody;
import com.example.holaserver.Store.Store;
import com.example.holaserver.Store.StoreService;
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
    // TODO: 순환참조 됨 store <-> item
    private final StoreService storeService;

    // TODO: 임시 저장 시에 새로운 rows 가 추가될 듯, 테스트 필요
    public Map<String, Object> saveItems(Long storeId, ItemSaveBody[] itemSaveBodies, Boolean isReady) {
        List<Item> items = Arrays.stream(itemSaveBodies).map(item -> {
            if (item.getId() == 0)
                return item.createSaveItemBuilder(storeId, isReady);
            else
                return item.updateSaveItemBuilder(storeId, isReady, item.getId());
        }).collect(Collectors.toList());
        ModelAndView result = new ModelAndView();
        List<Long> itemIds = itemRepository.saveAll(items).stream().map(Item::getId)
                .collect(Collectors.toList());
        result.addObject("itemIds", itemIds);
        storeService.updateStoreStatusById(storeId, isReady);
        return result.getModel();
    }
}
