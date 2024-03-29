package study.myproject.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import study.myproject.domain.item.Item;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int orderQuantity;

    public static OrderItem createOrderItem(Item item, int orderPrice, int orderQuantity) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .orderQuantity(orderQuantity)
                .build();

        item.removeStockQuantity(orderQuantity);
        return orderItem;
    }

    public void cancel() {
        getItem().addStockQuantity(orderQuantity);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getOrderQuantity();
    }
}
