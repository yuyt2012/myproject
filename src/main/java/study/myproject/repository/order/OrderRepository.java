package study.myproject.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
