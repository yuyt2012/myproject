package study.myproject.repository.Item;

import static org.springframework.util.StringUtils.hasText;
import static study.myproject.domain.item.QItem.*;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import study.myproject.dto.itemdto.ItemSearchCondition;
import study.myproject.dto.itemdto.ItemSearchResult;
import study.myproject.dto.itemdto.QItemSearchResult;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemSearchResult> findByCondition(Pageable pageable, ItemSearchCondition itemSearchCondition) {
        List<ItemSearchResult> content = queryFactory
                .select(new QItemSearchResult(
                        item.id.as("itemId"),
                        item.name,
                        item.price,
                        item.stockQuantity))
                .from(item)
                .where(
                        itemNameEq(itemSearchCondition.getItemName()),
                        priceGoe(itemSearchCondition.getPriceGoe()),
                        priceLoe(itemSearchCondition.getPriceLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(item.count())
                .from(item)
                .where(
                        itemNameEq(itemSearchCondition.getItemName()),
                        priceGoe(itemSearchCondition.getPriceGoe()),
                        priceLoe(itemSearchCondition.getPriceLoe()));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression itemNameEq(String itemName) {
        return hasText(itemName) ? item.name.eq(itemName) : null;
    }

    private BooleanExpression priceGoe(Integer priceGoe) {
        return priceGoe != null ? item.price.goe(priceGoe) : null;
    }

    private BooleanExpression priceLoe(Integer priceLoe) {
        return priceLoe != null ? item.price.loe(priceLoe) : null;
    }
}
