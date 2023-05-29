package com.tosinsa.toy.service;

import com.tosinsa.toy.domain.Item;
import com.tosinsa.toy.domain.ItemRepository;

import com.tosinsa.toy.web.ItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    @Transactional
    public Long saveItem(ItemForm itemForm) {
        Item item = Item.createItem(itemForm.getName(), itemForm.getPrice(), itemForm.getStockQuantity());
        Item saveItem = itemRepository.save(item);
        return saveItem.getId();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

}