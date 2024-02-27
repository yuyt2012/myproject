package study.myproject.dto.itemdto;

import lombok.Data;

@Data
public class ItemSearchCondition {

    private String itemName;
    private Integer priceGoe;
    private Integer priceLoe;
}
