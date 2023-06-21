package com.tosinsa.toy.service;

import com.tosinsa.toy.domain.file.FileStore;
import com.tosinsa.toy.domain.file.UploadFile;
import com.tosinsa.toy.domain.item.Item;
import com.tosinsa.toy.domain.item.ItemRepository;

import com.tosinsa.toy.web.ItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final FileStore fileStore;
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(ItemForm itemForm, MultipartFile multipartFile) throws Exception{
        UploadFile uploadFile = fileStore.storeFile(multipartFile);
        Item item = Item.createItem(itemForm.getName(), itemForm.getPrice(), itemForm.getStockQuantity(), uploadFile.getStoreFileName(), uploadFile.getUploadFileName());
        Item saveItem = itemRepository.save(item);
        return saveItem.getId();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public String getStoreFileName(String fileName){
        return fileStore.getFullPath(fileName);
    }

}