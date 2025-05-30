package com.easy.query.core.func.def.impl;

import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.func.column.ColumnExpression;
import com.easy.query.core.func.def.AbstractExpressionSQLFunction;
import com.easy.query.core.func.def.enums.DateTimeUnitEnum;
import com.easy.query.core.util.EasyCollectionUtil;

import java.util.List;

/**
 * create time 2023/12/21 11:58
 * 文件说明
 *
 * @author xuejiaming
 */
public class DateTimePropertySQLFunction extends AbstractExpressionSQLFunction {
    private final List<ColumnExpression> columnExpressions;
    private final DateTimeUnitEnum dateTimeUnitEnum;

    public DateTimePropertySQLFunction(List<ColumnExpression> columnExpressions, DateTimeUnitEnum dateTimeUnitEnum) {

        this.columnExpressions = columnExpressions;
        this.dateTimeUnitEnum = dateTimeUnitEnum;
    }

    @Override
    public String sqlSegment(TableAvailable defaultTable) {
        if (EasyCollectionUtil.isEmpty(columnExpressions)) {
            throw new IllegalArgumentException("columnExpressions is empty");
        }
        switch (dateTimeUnitEnum) {
            case DayOfYear:
                return "DAYOFYEAR({0})";
            case DayOfWeek:
                return "(DAYOFWEEK({0})-1)";
            case DayOfWeekSunDayLastDay:
                return "(WEEKDAY({0})+1)";
            case Year:
                return "YEAR({0})";
            case Month:
                return "MONTH({0})";
            case Day:
                return "DAYOFMONTH({0})";
            case Hour:
                return "HOUR({0})";
            case Minute:
                return "MINUTE({0})";
            case Second:
                return "SECOND({0})";
        }
        throw new UnsupportedOperationException("不支持当前属性获取:" + dateTimeUnitEnum);
    }

    @Override
    public int paramMarks() {
        return columnExpressions.size();
    }

    @Override
    protected List<ColumnExpression> getColumnExpressions() {
        return columnExpressions;
    }

}
