package study.myproject;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.myproject.domain.item.Book;
import study.myproject.domain.item.Item;
import study.myproject.domain.order.Delivery;
import study.myproject.domain.order.Order;
import study.myproject.domain.order.OrderItem;
import study.myproject.repository.Item.ItemRepository;

@Component
@RequiredArgsConstructor
public class InitData {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final ItemRepository itemRepository;

        public void dbInit1() {
            Item book = new Book();
            book.set
        }
}
