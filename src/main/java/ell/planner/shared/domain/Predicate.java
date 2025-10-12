package ell.planner.shared.domain;

import ell.planner.shared.domain.enums.Operator;
import lombok.Getter;

@Getter
public class Predicate {
    private final String field;
    private final Operator operator;
    private final Object value;

    private Predicate(String field, Operator operator, Object value) {
        //TODO 입력값 검증 추가
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public static Predicate of(String field, Operator operator, Object value) {
        return new Predicate(field, operator, value);
    }
}
