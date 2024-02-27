package study.myproject.repository.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.myproject.dto.itemdto.ItemSearchCondition;
import study.myproject.dto.itemdto.ItemSearchResult;

public interface ItemRepositoryCustom {

    Page<ItemSearchResult> findByCondition(Pageable pageable, ItemSearchCondition itemSearchCondition);
}
