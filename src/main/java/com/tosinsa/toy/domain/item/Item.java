package com.tosinsa.toy.domain.item;

import com.tosinsa.toy.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String storeFileName;
    private String uploadFileName;


    //==비즈니스 로직==//
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    @Builder
    public Item(String name, int price, int stockQuantity,String storeFileName, String uploadFileName){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.storeFileName = storeFileName;
        this.uploadFileName = uploadFileName;
    }

    public static Item createItem(String name, int price, int stockQuantity, String storeFileName, String uploadFileName){
        Item item = Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .storeFileName(storeFileName)
                .uploadFileName(uploadFileName)
                .build();
        return item;
    }

    public void updateItem(String name, int price, int stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
