package study.myproject.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myproject.domain.item.Category;
import study.myproject.domain.item.Item;
import study.myproject.dto.itemdto.ItemSearchCondition;
import study.myproject.dto.itemdto.ItemSearchResult;
import study.myproject.repository.Item.CategoryRepository;
import study.myproject.repository.Item.ItemRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public void saveItem(Item item, Category category) {
        itemRepository.save(item);
        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Page<ItemSearchResult> findItem(Pageable pageable, ItemSearchCondition itemSearchCondition) {
        return itemRepository.findByCondition(pageable, itemSearchCondition);
    }
}
