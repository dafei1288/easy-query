package com.easy.query.test.common;

import com.easy.query.core.expression.sql.include.RelationValue;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * create time 2024/10/16 17:14
 * 当且仅当selfProperty为长度为1或者空(主键情况)的数组时才会使用当前对象
 * 比如:selfProperty=["id"]那么value为id的值
 *
 * @author xuejiaming
 */
public class MySingleRelationValue implements RelationValue {
    protected final Object value;

    public MySingleRelationValue(Object value) {
        this.value = value;
    }

    @Override
    public List<Object> getValues() {
        return Collections.singletonList(value);
    }

    @Override
    public boolean isNull() {
        return Objects.isNull(value) || Objects.equals("-", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySingleRelationValue that = (MySingleRelationValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "SingleRelationValue{" +
                "value=" + value +
                '}';
    }
}
