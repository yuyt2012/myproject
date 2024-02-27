package study.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.myproject.dto.itemdto.ItemSearchCondition;
import study.myproject.dto.itemdto.ItemSearchResult;
import study.myproject.service.item.ItemService;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/item/search")
    public ResponseEntity<Object> findItem(Pageable pageable, @RequestBody ItemSearchCondition itemSearchCondition) {
        try {
            Page<ItemSearchResult> item = itemService.findItem(pageable, itemSearchCondition);
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
