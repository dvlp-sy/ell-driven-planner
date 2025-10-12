package ell.planner.shared.domain;

import ell.planner.shared.domain.enums.SortOrder;
import lombok.Getter;

@Getter
public class SortBy {
    private final String field;
    private final SortOrder order;

    private SortBy(String field, SortOrder order) {
        this.field = field;
        this.order = order;
    }

    public static SortBy of(String field, SortOrder order) {
        return new SortBy(field, order);
    }
}
