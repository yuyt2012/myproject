package study.myproject.dto.itemdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSaveDTO {

    private String name;
    private int price;
    private int stockQuantity;
}
