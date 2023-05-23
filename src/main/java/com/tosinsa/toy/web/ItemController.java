package com.tosinsa.toy.web;

import com.tosinsa.toy.domain.Item;
import com.tosinsa.toy.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new ItemForm());
        return "items/createItemForm";
    }

    @PostMapping("/items")
    public String create(ItemForm itemForm){
        itemService.saveItem(itemForm);
        return "/home";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Item item = itemService.findOne(itemId);
        ItemForm form = new ItemForm();
        form.setItemId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PutMapping ("items/{itemId}")
    public String updateItem(@PathVariable("itemId") Long itemId,ItemForm form){
        Item item = itemService.findOne(itemId);
        item.updateItem(form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }
}
