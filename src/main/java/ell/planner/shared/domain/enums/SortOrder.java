package ell.planner.shared.domain.enums;

import lombok.Getter;

@Getter
public enum SortOrder {
    ASC(1),
    DESC(-1),
    ;

    private final Integer intValue;

    SortOrder(Integer intValue) {
        this.intValue = intValue;
    }
}
