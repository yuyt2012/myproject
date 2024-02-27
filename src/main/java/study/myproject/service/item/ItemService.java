package study.myproject.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myproject.domain.item.Item;
import study.myproject.dto.itemdto.ItemSearchCondition;
import study.myproject.dto.itemdto.ItemSearchResult;
import study.myproject.repository.Item.ItemRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public Page<ItemSearchResult> findItem(Pageable pageable, ItemSearchCondition itemSearchCondition) {
        return itemRepository.findByCondition(pageable, itemSearchCondition);
    }
}
