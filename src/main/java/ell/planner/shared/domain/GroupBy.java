package ell.planner.shared.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class GroupBy {
    private final List<String> fields;

    private GroupBy(List<String> fields) {
        //TODO 입력값 검증 추가
        this.fields = fields;
    }

    public static GroupBy from(List<String> fields) {
        return new GroupBy(fields);
    }
}
