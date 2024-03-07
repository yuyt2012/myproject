package study.myproject.domain.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import study.myproject.exception.NotEnoughStockException;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStockQuantity(int quantity) {
        int restStockQuantity = this.stockQuantity - quantity;
        if (restStockQuantity < 0) {
            throw new NotEnoughStockException();
        }
        this.stockQuantity = restStockQuantity;
    }
}
