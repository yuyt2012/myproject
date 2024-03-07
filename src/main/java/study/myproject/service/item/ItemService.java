package study.myproject.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.myproject.domain.item.Item;
import study.myproject.dto.itemdto.ItemSaveDTO;
import study.myproject.repository.Item.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemSaveDTO saveItem(ItemSaveDTO itemSaveDTO) {
        Item item = Item.builder()
                .name(itemSaveDTO.getName())
                .price(itemSaveDTO.getPrice())
                .stockQuantity(itemSaveDTO.getStockQuantity())
                .build();
        itemRepository.save(item);
        return itemSaveDTO;
    }
}
