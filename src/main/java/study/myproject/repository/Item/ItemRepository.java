package study.myproject.repository.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
