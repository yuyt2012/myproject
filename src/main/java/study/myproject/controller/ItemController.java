package study.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.myproject.dto.itemdto.ItemSaveDTO;
import study.myproject.service.item.ItemService;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/api/item/save")
    public ResponseEntity<ItemSaveDTO> saveItem(@RequestBody ItemSaveDTO itemSaveDTO) {
        ItemSaveDTO result = itemService.saveItem(itemSaveDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
