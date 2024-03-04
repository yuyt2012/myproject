package study.myproject.repository.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.domain.item.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
