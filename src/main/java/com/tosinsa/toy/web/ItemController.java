package com.tosinsa.toy.web;

import com.tosinsa.toy.domain.item.Item;
import com.tosinsa.toy.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/add")
    public String addItemForm(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        return "items/addItemForm";
    }

    @PostMapping("/items")
    public String create(@Validated @ModelAttribute ItemForm itemForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, MultipartFile multipartFile) throws Exception{
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "items/addItemForm";
        }
        Long itemId = itemService.saveItem(itemForm,multipartFile);
        redirectAttributes.addAttribute("itemId",itemId);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}")
    public String readItem(@PathVariable("itemId") Long itemId, Model model){
        Item item = itemService.findOne(itemId);
        ItemForm itemForm = new ItemForm();
        itemForm.setItemId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setPrice(item.getPrice());
        itemForm.setStockQuantity(item.getStockQuantity());
        itemForm.setStoreFileName(item.getStoreFileName());
        System.out.println(item.getStoreFileName());
        model.addAttribute("itemForm", itemForm);
        return "items/item";
    }

    @PutMapping ("items/{itemId}")
    public String updateItem(@PathVariable("itemId") Long itemId,ItemForm form){
        Item item = itemService.findOne(itemId);
        item.updateItem(form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }

    @ResponseBody
    @GetMapping("/files/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        // file:/User/.../file/1230-31421-324-3124.png
        return new UrlResource("file:" + itemService.getStoreFileName(filename));
    }


}
