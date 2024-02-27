package study.myproject.dto.itemdto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ItemSearchResult {

    private Long itemId;
    private String itemName;
    private int price;
    private int stockQuantity;


    @QueryProjection
    public ItemSearchResult(Long itemId, String itemName, int price, int stockQuantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
